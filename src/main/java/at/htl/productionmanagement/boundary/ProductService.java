package at.htl.productionmanagement.boundary;

import at.htl.productionmanagement.entity.Product;
import at.htl.productionmanagement.repository.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("api/product")
public class ProductService {
    @Inject
    ProductRepository repository;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Product> findAllProducts() {
        return repository.getProducts();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findProduct(@PathParam("id") Long id) {
        Product productToBeFound = repository.findByProductId(id);
        if(productToBeFound == null){
            return Response.noContent().build();
        }
        return Response.ok(repository.findByProductId(id)).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addProduct(Product product, @Context UriInfo uriInfo){
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder()
                .path(product.getProductId().toString());
        if(!repository.addProduct(product)){
            return Response.notModified("The product " + product.getProductName() + " exists").build();
        }
        return Response.ok("The product was created").build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateProducts(@PathParam("id") Long id,Product product){
        if(!repository.updateProducts(id, product)){
            return Response.notModified("The product "+ repository.findByProductId(id).getProductName() + "doesn't exist or is out of stock").build();
        }
        return Response.ok("The product "+ repository.findByProductId(id).getProductName() + " is available").build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProducts(@PathParam("id") Long id) {
        boolean isDeleted = repository.deleteProducts(id);
        if (!isDeleted) {
            return Response.notModified("The product with the ID " + id + "doesn't exist or is already out of stock").build();
        }
        return Response.ok("The product with the ID " + id + " was deleted").build();
    }
}

package at.htl.productionmanagement.boundary;

import at.htl.productionmanagement.entity.OrderDetails;
import at.htl.productionmanagement.repository.OrderDetailsRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("api/order-details")
public class OrderDetailsService {
    @Inject
    OrderDetailsRepository repository;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<OrderDetails> findAllOrderDetailss() {
        return repository.getOrderDetails();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findOrderDetails(@PathParam("id") Long id) {
        OrderDetails OrderDetailsToBeFound = repository.findByOrderDetailsId(id);
        if(OrderDetailsToBeFound == null){
            return Response.noContent().build();
        }
        return Response.ok(repository.findByOrderDetailsId(id)).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrderDetails(OrderDetails OrderDetails, @Context UriInfo uriInfo){
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder()
                .path(OrderDetails.getId().toString());
        if(!repository.addOrderDetails(OrderDetails)){
            return Response.notModified("The order details already exists").build();
        }
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateOrderDetails(@PathParam("id") Long id, OrderDetails OrderDetails){
        if(!repository.updateOrderDetails(id, OrderDetails)){
            return Response.notModified("The order details " + repository.findByOrderDetailsId(id).getId() + " weren't updated").build();
        }
        return Response.ok("The order details "+ repository.findByOrderDetailsId(id).getId() + " were updated").build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteOrderDetails(@PathParam("id") Long id) {
        boolean isDeleted = repository.deleteOrderDetails(id);
        if (!isDeleted) {
            return Response.notModified("The order details with the ID " + id + " don't exist").build();
        }
        return Response.ok("The order details with the ID " + id + " were deleted").build();
    }
}

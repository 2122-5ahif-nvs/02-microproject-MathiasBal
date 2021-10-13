package at.htl.productionmanagement.boundary;

import at.htl.productionmanagement.entity.Order;
import at.htl.productionmanagement.repository.OrderRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("api/order")
public class OrderService {
    @Inject
    OrderRepository repository;

    @Operation(
            summary = "Return all orders"
    )
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Order> findAllOrders() {
        return repository.getOrders();
    }

    @Operation(
            summary = "Return order with specific id"
    )
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findOrder(@PathParam("id") Long id) {
        Order OrderToBeFound = repository.findByOrderNumber(id);
        if(OrderToBeFound == null){
            return Response.noContent().build();
        }
        return Response.ok(repository.findByOrderNumber(id)).build();
    }

    @Operation(
            summary = "Issue a new order"
    )
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addOrder(Order Order, @Context UriInfo uriInfo){
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder()
                .path(Order.getOrderNumber().toString());
        if(!repository.addOrder(Order)){
            return Response.notModified("Order: " + Order.getOrderNumber() + " has already been placed").build();
        }
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateOrders(@PathParam("id") Long id,Order Order){
        if(!repository.updateOrders(id, Order)){
            return Response.notModified("Order: "+ repository.findByOrderNumber(id).getOrderNumber() + " doesn't exist or has not been placed").build();
        }
        return Response.ok("Order: "+ repository.findByOrderNumber(id).getOrderNumber() + " has been changed").build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteOrder(@PathParam("id") Long orderNumber) {
        boolean isDeleted = repository.deleteOrders(orderNumber);
        if (!isDeleted) {
            return Response.notModified("Order: " + orderNumber + "doesn't exist or has already been deleted").build();
        }
        return Response.ok("Order: " + orderNumber + " has been deleted").build();
    }
}

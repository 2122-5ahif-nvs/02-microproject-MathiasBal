package at.htl.productionmanagement.boundary;

import at.htl.productionmanagement.entity.Client;
import at.htl.productionmanagement.repository.ClientRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("api/client")
public class ClientService {
    @Inject
    ClientRepository repository;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Client> findAllClients() {
        return repository.getClients();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findClient(@PathParam("id") Long id) {
        Client clientToBeFound = repository.findByClientId(id);
        if(clientToBeFound == null){
            return Response.noContent().build();
        }
        return Response.ok(repository.findByClientId(id)).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addClient(Client client, @Context UriInfo uriInfo){
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder()
                .path(client.getId().toString());
        if(!repository.addClient(client)){
            return Response.notModified("The client " + client.getClientName() + " is already in our contact list").build();
        }
        return Response.ok("The client was created").build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateClients(@PathParam("id") Long id,Client client){
        if(!repository.updateClients(id, client)){
            return Response.notModified("The client "+ repository.findByClientId(id).getClientName() + "doesn't exist or has never ordered from us").build();
        }
        return Response.ok(repository.findByClientId(id).getClientName() + "'s information has been updated").build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteClientByID(@PathParam("id") Long id) {
        Client client = repository.findByClientId(id);
        if (client == null) {
            return Response.notModified(id + " doesn't exist or has been removed from the contact list").build();
        }
        repository.delete(client);
        return Response.ok(id + " was been removed from the contact list").build();
    }
}

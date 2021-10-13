package at.htl.productionmanagement.repository;

import at.htl.productionmanagement.entity.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client> {
    private List<Client> clientList;

    public ClientRepository() {
        clientList = new LinkedList<>();
    }

    public boolean addClient(Client client) {
        if (findByClientId(client.getId()) != null){
            return false;
        }
        this.persist(client);
        return true;
    }

    public Client findByClientId(Long id){
        try{
            return this.findById(id);
        }catch(NotFoundException e){
            return null;
        }
    }

    public List<Client> getClients() { return Collections.unmodifiableList(clientList); }

    public boolean updateClients(Long id, Client newClient){
        Client clientToUpdate = findByClientId(id);
        if (clientToUpdate == null){
            return false;
        }
        clientToUpdate.setClientName(newClient.getClientName());
        clientToUpdate.setAddress(newClient.getAddress());
        return true;
    }
}

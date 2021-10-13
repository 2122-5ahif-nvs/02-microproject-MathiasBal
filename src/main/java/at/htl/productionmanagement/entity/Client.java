package at.htl.productionmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String address;

    public Client(Long id, String clientName, String address) {
        this.id = id;
        this.clientName = clientName;
        this.address = address;
    }

    public Client() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client: " + clientName + ", " + id + ", " + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return getId().equals(client.getId()) &&
                getClientName().equals(client.getClientName()) &&
                getAddress().equals(client.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getClientName(), getAddress());
    }
}

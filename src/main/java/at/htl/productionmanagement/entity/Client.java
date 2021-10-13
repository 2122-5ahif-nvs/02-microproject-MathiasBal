package at.htl.productionmanagement.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
@Schema(description = "Store information about a client.")
public class Client {
    @Id
    @Schema(required = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;

    @Schema(required = true)
    @JsonbProperty("client_name")
    @Column(name = "c_name")
    private String clientName;

    @Schema(required = true)
    @Column(name = "address")
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

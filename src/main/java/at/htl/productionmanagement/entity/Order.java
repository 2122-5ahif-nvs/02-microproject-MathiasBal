package at.htl.productionmanagement.entity;

import at.htl.productionmanagement.repository.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "Orders")
@XmlRootElement
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private Long orderNumber;
    @ManyToOne
    private Client client;

    @XmlJavaTypeAdapter(type=LocalDate.class, value= LocalDateAdapter.class)
    private LocalDate orderDate;

    public Order(Long orderNumber, Client client, LocalDate orderDate) {
        this.orderNumber = orderNumber;
        this.client = client;
        this.orderDate = orderDate;
    }

    public Order() {
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order: " + orderNumber + ", " + client + ", " + orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getOrderNumber().equals(order.getOrderNumber()) &&
                getClient().equals(order.getClient()) &&
                getOrderDate().equals(order.getOrderDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderNumber(), getClient(), getOrderDate());
    }
}

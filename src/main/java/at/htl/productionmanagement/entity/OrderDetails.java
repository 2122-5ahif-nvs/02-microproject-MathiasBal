package at.htl.productionmanagement.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private Long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    public OrderDetails(Long id, Order order, Product product) {
        this.id = id;
        this.order = order;
        this.product = product;
    }

    public OrderDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetails: " + id + ", " + order + ", " + product;
    }
}

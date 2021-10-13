package at.htl.productionmanagement.repository;

import at.htl.productionmanagement.entity.OrderDetails;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class OrderDetailsRepository implements PanacheRepository<OrderDetails> {
    private List<OrderDetails> orderDetailsList;

    public OrderDetailsRepository() {
        orderDetailsList = new LinkedList<>();
    }

    public boolean addOrderDetails(OrderDetails orderDetails) {
        if (findByOrderDetailsId(orderDetails.getId()) != null){
            return false;
        }
        this.persist(orderDetails);
        return true;
    }

    public OrderDetails findByOrderDetailsId(Long id){
        try{
            return this.findById(id);
        }catch(NotFoundException e){
            return null;
        }
    }

    public List<OrderDetails> getOrderDetails() { return Collections.unmodifiableList(orderDetailsList); }

    public boolean updateOrderDetails(Long id, OrderDetails newOrderDetails){
        OrderDetails orderDetailsToUpdate = findByOrderDetailsId(id);
        if (orderDetailsToUpdate == null){
            return false;
        }
        orderDetailsToUpdate.setOrder(newOrderDetails.getOrder());
        orderDetailsToUpdate.setProduct(newOrderDetails.getProduct());
        return true;
    }

    public boolean deleteOrderDetails(Long id){
        return orderDetailsList.removeIf(p -> p.getId().equals(id));
    }

    public void clearOrderDetails(){
        orderDetailsList = new LinkedList<>();
    }
}

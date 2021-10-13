package at.htl.productionmanagement.repository;


import at.htl.productionmanagement.entity.Order;
import com.sun.xml.bind.v2.schemagen.episode.Package;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
    private List<Order> orderList;

    public OrderRepository() {
        orderList = new LinkedList<>();
    }

    public boolean addOrder(Order order) {
        if (findByOrderNumber(order.getOrderNumber()) != null){
            return false;
        }
        this.persist(order);
        return true;
    }

    public Order findByOrderNumber(Long id){
        try{
            return this.findById(id);
        }catch(NotFoundException e){
            return null;
        }
    }

    public List<Order> getOrders() { return Collections.unmodifiableList(orderList); }

    public boolean updateOrders(Long id, Order newOrder){
        Order OrderToUpdate = findByOrderNumber(id);
        if (OrderToUpdate == null){
            return false;
        }
        OrderToUpdate.setClient(newOrder.getClient());
        OrderToUpdate.setOrderDate(newOrder.getOrderDate());
        return true;
    }

    public boolean deleteOrders(Long id){
        return orderList.removeIf(p -> p.getOrderNumber().equals(id));
    }

    public void clearOrders(){
        orderList = new LinkedList<>();
    }
}

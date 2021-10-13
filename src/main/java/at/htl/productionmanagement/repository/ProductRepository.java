package at.htl.productionmanagement.repository;

import at.htl.productionmanagement.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {
    private List<Product> productList;

    public ProductRepository() {
        productList = new LinkedList<>();
    }

    public boolean addProduct(Product product) {
        if (findByProductId(product.getProductId()) != null){
            return false;
        }
        productList.add(product);
        return true;
    }

    public Product findByProductId(Long id){
        try{
            return this.findById(id);
        }catch(NotFoundException e){
            return null;
        }
    }

    public List<Product> getProducts() { return Collections.unmodifiableList(productList); }

    public boolean updateProducts(Long id, Product newProduct){
        Product productToUpdate = findByProductId(id);
        if (productToUpdate == null){
            return false;
        }
        productToUpdate.setTotalCost(newProduct.getTotalCost());
        productToUpdate.setProductName(newProduct.getProductName());
        productToUpdate.setProductionCost(newProduct.getProductionCost());
        productToUpdate.setMaterialType(newProduct.getMaterialType());
        return true;
    }

    public boolean deleteProducts(Long id){
        return productList.removeIf(p -> p.getProductId().equals(id));
    }

    public void clearProducts(){
        productList = new LinkedList<>();
    }
}

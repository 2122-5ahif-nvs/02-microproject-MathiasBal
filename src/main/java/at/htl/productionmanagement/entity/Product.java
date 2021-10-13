package at.htl.productionmanagement.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@XmlRootElement
public class Product {
    @Id
    @Schema(required = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(required = true)
    @JsonbProperty("product_name")
    private String productName;

    @Schema(required = true)
    @JsonbProperty("material_type")
    private String materialType;

    @Schema(required = true)
    @JsonbProperty("production_cost")
    private double productionCost;

    @Schema(required = true)
    @JsonbProperty("total_cost")
    private double totalCost;

    public Product(String productName, Long productId, String materialType, double productionCost, double totalCost) {
        this.productName = productName;
        this.id = productId;
        this.materialType = materialType;
        this.productionCost = productionCost;
        this.totalCost = totalCost;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() { return id; }

    public void setProductId(Long productID) { this.id = productID; }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public double getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Product: " + productName + ", " + id + ", " + materialType + ", " + productionCost + ", " + totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getProductionCost(), getProductionCost()) == 0 &&
                Double.compare(product.getTotalCost(), getTotalCost()) == 0 &&
                getProductName().equals(product.getProductName()) &&
                getProductId().equals(product.getProductId()) &&
                getMaterialType().equals(product.getMaterialType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName(), getProductId(), getMaterialType(), getProductionCost(), getTotalCost());
    }
}

package son.nguyen.webseller.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String productName;
    @Column
    private String productInfo;

    @Column
    private String productType;
    @Column
    private String imageProduct;
    @Column
    private String providerName;

    @OneToMany( mappedBy = "products",cascade = CascadeType.ALL)
    private List<Prices> prices;
    @OneToMany( mappedBy = "products",cascade = CascadeType.ALL)
    private List<Promotions> promotions;
    @OneToOne (mappedBy = "products",cascade =  CascadeType.ALL)
    private ProductDetail productDetail;

    @JsonBackReference
    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    @JsonManagedReference
    public List<Promotions> getPromotions() {
        return promotions;
    }


    public void setPromotions(List<Promotions> promotions) {
        this.promotions = promotions;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
    @JsonManagedReference
    public List<Prices> getPrices() {
        return prices;
    }

    public void setPrices(List<Prices> prices) {
        this.prices = prices;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productInfo='" + productInfo + '\'' +
                ", productType='" + productType + '\'' +
                ", imageProduct='" + imageProduct + '\'' +
                ", providerName='" + providerName + '\'' +
                ", prices=" + prices +
                ", promotions=" + promotions +
                '}';
    }
}

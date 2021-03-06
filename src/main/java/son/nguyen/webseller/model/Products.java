package son.nguyen.webseller.model;

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
    @ElementCollection
    private List<String> productInfo;

    @Column
    private String productType;
    @Column
    private String code;
    @Column
    private Long barcode;
    @Column
    private String tags;
     @Column
     private int status;
    @Column
    private String imageProduct;
    @Column
    private String providerName;
    @Column String href;

    @OneToOne( mappedBy = "products",cascade = CascadeType.ALL)
    private Prices prices;
    @OneToMany( mappedBy = "products",cascade = CascadeType.ALL)
    private List<Promotions> promotions;
    @OneToOne (mappedBy = "products",cascade =  CascadeType.ALL)
    private ProductDetail productDetail;

    public Long getBarcode() {
        return barcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @JsonManagedReference
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

    public List<String> getProductInfo() {
        return productInfo;
    }
    public String getInfo(){
        StringBuffer stringBuffer=new StringBuffer();
        for (String a:this.getProductInfo()){
            stringBuffer.append(a).append(" ");
        }
        String s = stringBuffer.toString().toLowerCase().replace("\"","\\\"").replace("●","");
return s;

    }

    public static void main(String[] args) {
        String x="\"abcd";
        String z=x.replace("\"", "");
        System.out.println(z);
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setProductInfo(List<String> productInfo) {
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
    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
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

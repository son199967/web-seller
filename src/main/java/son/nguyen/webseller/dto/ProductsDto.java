package son.nguyen.webseller.dto;

import java.util.List;

public class ProductsDto {
    private long id;

    private String productName;

    private String productInfo;

    private String productType;

    private String imageProduct;

    private String providerName;

    private List<PricesDto> pricesDtos;

    private List<PromotionsDto> promotionsDtos;

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

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public List<PromotionsDto> getPromotionsDtos() {
        return promotionsDtos;
    }

    public void setPromotionsDtos(List<PromotionsDto> promotionsDtos) {
        this.promotionsDtos = promotionsDtos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PricesDto> getPricesDtos() {
        return pricesDtos;
    }

    public void setPricesDtos(List<PricesDto> pricesDtos) {
        this.pricesDtos = pricesDtos;
    }

    @Override
    public String toString() {
        return "ProductsDto{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productInfo='" + productInfo + '\'' +
                ", productType='" + productType + '\'' +
                ", imageProduct='" + imageProduct + '\'' +
                ", providerName='" + providerName + '\'' +
                ", pricesDtos=" + pricesDtos +
                ", promotionsDtos=" + promotionsDtos +
                '}';
    }
}

package son.nguyen.webseller.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PricesDto {
    private long id;
    private BigDecimal unitPrice;
    private Date dateStart;
    private Date dateEnd;
    private ProductsDto productsDto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ProductsDto getProductsDto() {
        return productsDto;
    }

    public void setProductsDto(ProductsDto productsDto) {
        this.productsDto = productsDto;
    }

    @Override
    public String toString() {
        return "PricesDto{" +
                "id=" + id +
                ", unitPrice=" + unitPrice +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", productsDto=" + productsDto +
                '}';
    }
}

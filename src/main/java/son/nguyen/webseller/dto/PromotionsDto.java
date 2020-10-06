package son.nguyen.webseller.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionsDto {
    private Long id;
    private String description;
    private Date dateStart;
    private Date dateEnd;
    private BigDecimal amount;
    private ProductsDto productsDto;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductsDto getProductsDto() {
        return productsDto;
    }

    public void setProductsDto(ProductsDto productsDto) {
        this.productsDto = productsDto;
    }

    @Override
    public String toString() {
        return "PromotionsDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", amount=" + amount +
                ", productsDto=" + productsDto +
                '}';
    }
}

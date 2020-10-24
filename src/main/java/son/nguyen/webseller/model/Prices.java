package son.nguyen.webseller.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="prices")
public class Prices implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private BigDecimal unitPrice;
    @Column
    private BigDecimal wholePrice;
    @Column
    private BigDecimal importPrice;
    @Column
    private BigDecimal oldPrice;

    @Column
    private int discout;

    public int getDiscout() {
        return discout;
    }

    public void setDiscout() {
      if (this.unitPrice.intValue()==0||this.oldPrice.intValue()==0){
          this.discout=0;
          return;
      }
      this.discout=(int)(((this.oldPrice.doubleValue()-this.unitPrice.doubleValue())/(this.getOldPrice().doubleValue()))*100);
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private Products products;

    public BigDecimal getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(BigDecimal wholePrice) {
        this.wholePrice = wholePrice;
    }

    public BigDecimal getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(BigDecimal importPrice) {
        this.importPrice = importPrice;
    }

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


    @JsonBackReference
    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "id=" + id +
                ", unitPrice=" + unitPrice +
                ", wholePrice=" + wholePrice +
                ", importPrice=" + importPrice +
                ", products=" + products +
                '}';
    }
}

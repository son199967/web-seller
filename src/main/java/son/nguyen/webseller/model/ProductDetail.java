package son.nguyen.webseller.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Table(name = "productDetail")
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection
    private List<String> images;
    @Column
    private  String content;
    @ElementCollection
    private List<String> color;
    @ElementCollection
    private List<Integer> size;
    @Column
    private String donvi;

    @ElementCollection
    private List<String> discription;
    @Column
    private int star;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products")
    private Products products;

    public List<String> getColor() {
        return color;
    }

    public List<Integer> getSize() {
        return size;
    }

    public List<String> getDiscription() {
        return discription;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public void setDiscription(List<String> discription) {
        this.discription = discription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
    @JsonBackReference
    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}

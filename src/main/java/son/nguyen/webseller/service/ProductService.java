package son.nguyen.webseller.service;

import son.nguyen.webseller.model.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Products> getAllProduct();
    Products saveProduct(Products products);
    Products update(Products products);
    Optional<Products> findById(Long id);
    Optional<Products> findByNameProduct(String name);
    List<Products> searchByContent(String content);
    List<Products> searchByCategory(String category,String brand,int priceFrom,int priceTo);
    List<String> getAllTypeProduct();
    Products getProductHref(String url);
    void deleteProductByid(Long id);



}

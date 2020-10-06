package son.nguyen.webseller.service;

import son.nguyen.webseller.model.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Products> getAllProduct();
    Products saveProduct(Products products);
    Optional<Products> findById(Long id);
    Optional<Products> findByNameProduct(String name);

}

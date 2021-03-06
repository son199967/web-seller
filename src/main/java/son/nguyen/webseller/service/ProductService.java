package son.nguyen.webseller.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import son.nguyen.webseller.model.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Products> getAllProduct(Integer status,Pageable pageable);
    Page<Products> getProductCate(String provi,String productType,String tag,Pageable pageable);
    Products saveProduct(Products products);
    Products update(Products products);
    Optional<Products> findById(Long id);
    Optional<Products> findByNameProduct(String name);
    List<Products> searchByContent(String content);
    List<Products> searchByCategory(String category,String brand,int priceFrom,int priceTo);
    List<String> getAllTypeProduct();
    Products getProductHref(String url);
    void deleteProductByid(Long id);
   List <String> getAllBranch(String tag,String provider,String productType);
   Products updateProductStatus(Long id,int status);
   List<Products> smartSearch(String search) throws JsonProcessingException;



}

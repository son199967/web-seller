package son.nguyen.webseller.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import son.nguyen.webseller.model.Products;
import son.nguyen.webseller.repository.ProductRepository;
import son.nguyen.webseller.service.ProductService;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Products> getAllProduct() {
       List<Products> products = productRepository.getAllProduct();
        System.out.println("sjhdsjd");
      return products;
    }

    @Override
    public Products saveProduct(Products products) {
        Products productSave=productRepository.save(products);
        return productSave;
    }

    @Override
    public Optional<Products> findById(Long id) {
        Optional<Products> products =productRepository.findById(id);
        return products;
    }

    @Override
    public Optional<Products> findByNameProduct(String name) {
        Optional<Products> products =productRepository.findByProductName(name);
        return products;
    }
}

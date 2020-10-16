package son.nguyen.webseller.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import son.nguyen.webseller.model.Products;
import son.nguyen.webseller.repository.ProductRepository;
import son.nguyen.webseller.service.ProductService;

import java.util.ArrayList;
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

    @Override
    public List<Products> searchByContent(String content) {
        List<Products> productsList =new ArrayList<>();
        if (content!=null) {
            productsList = productRepository.searchProduct("%" + content + "%");
        }else {
            productsList=productRepository.getAllProduct();
        }
        return  productsList;
    }

    @Override
    public List<Products> searchByCategory(String category, String brand, int priceFrom, int priceTo) {
        List<Products> products=new ArrayList<>();
        if (category!=null&& brand!=null) {
          products = productRepository.searcgProductBy1(category, brand, priceFrom, priceTo);
        }else if(category!=null&&brand==null){
            products = productRepository.searcgProductBy2(category, priceFrom, priceTo);
        }
        return  products;
    }
}

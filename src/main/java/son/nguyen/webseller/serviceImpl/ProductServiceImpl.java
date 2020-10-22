package son.nguyen.webseller.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import son.nguyen.webseller.model.Products;
import son.nguyen.webseller.repository.ProductDetailRepository;
import son.nguyen.webseller.repository.ProductRepository;
import son.nguyen.webseller.service.ProductService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductDetailRepository productDetailRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public Page<Products> getAllProduct(Pageable pageable) {
        Page<Products> products = productRepository.getAllProduct(pageable);
        System.out.println("sjhdsjd");
        return products;
    }

    @Override
    public Products saveProduct(Products products) {
        Products productSave = productRepository.save(products);
        return productSave;
    }

    @Override
    public Optional<Products> findById(Long id) {
        Optional<Products> products = productRepository.findById(id);
        return products;
    }

    @Override
    public Optional<Products> findByNameProduct(String name) {
        Optional<Products> products = productRepository.findByProductName(name);
        return products;
    }

    @Override
    public List<Products> searchByContent(String content) {
        List<Products> productsList = new ArrayList<>();
        Pageable pageable=PageRequest.of(1,20);
        if (content != null) {
            productsList = productRepository.searchProduct("%" + content + "%");
        }
//        else {
//            productsList = productRepository.getAllProduct(pageable);
//        }
        return productsList;
    }

    @Override
    public List<Products> searchByCategory(String category, String brand, int priceFrom, int priceTo) {
        List<Products> products = new ArrayList<>();
        if (category != null && brand != null) {
            products = productRepository.searcgProductBy1(category, brand, priceFrom, priceTo);
        } else if (category != null && brand == null) {
            products = productRepository.searcgProductBy2(category, priceFrom, priceTo);
        }
        return products;
    }

    @Override
    public List<String> getAllTypeProduct() {
        List<String> stringList = new ArrayList<>();
        stringList = productRepository.getAllTypeProduct().stream().distinct().collect(Collectors.toList());
        return stringList;
    }

    @Override
    public void deleteProductByid(Long id) {
        Optional<Products> products = productRepository.findById(id);
        if (!products.isPresent()) return;
//        productDetailRepository.deleteById(products.get().getProductDetail().getId());
        productRepository.deleteById(id);
        return;
    }

    @Override
    public Products update(Products products) {
        Products products1 = productRepository.findById(products.getId()).get();
        products.setId(products1.getId());
         productRepository.save(products);
        return products1;
    }

    @Override
    public Products getProductHref(String url) {
        Optional<Products> products= productRepository.getByhref(url);
        if (products.isPresent())
            return products.get();
        return null;
    }

    @Override
    public Page<Products> getProductCate(String provide,String cate, Pageable pageable) {
        Page<Products> products=null;
        if (provide=="") {
            products = productRepository.getProductCate(cate, pageable);
        }else {
            products = productRepository.getProductCateProvide(provide, cate, pageable);
        }
        return products;
    }
    @Override
    public List<String> getAllBranch(String cate) {
        List<String> products=productRepository.getAllBranch( cate).stream().distinct().collect(Collectors.toList());

        return products;
    }
}

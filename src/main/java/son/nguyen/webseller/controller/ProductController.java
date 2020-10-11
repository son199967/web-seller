package son.nguyen.webseller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import son.nguyen.webseller.model.Products;
import son.nguyen.webseller.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/getAllProduct")
    private ResponseEntity<List<Products>> getAllProduct(){
        List<Products> products= productService.getAllProduct();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProductById/{id}")
    private ResponseEntity<Products> getProductById(@PathVariable("id") Long id){
        Optional<Products> product= productService.findById(id);
        return ResponseEntity.ok(product.get());
    }
    @PostMapping("/updateProduce")
    private ResponseEntity<Products> updateProduct(@RequestBody Products products){
        Products product=null;
        try {
             product = productService.saveProduct(products);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

         return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}

package son.nguyen.webseller.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import son.nguyen.webseller.config.sercurity.JwtTokenUtil;
import son.nguyen.webseller.dto.UserDto;
import son.nguyen.webseller.model.Products;
import son.nguyen.webseller.service.JwtUserDetailsService;
import son.nguyen.webseller.service.ProductService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsService userDetailsService;
    @Autowired
    public ProductController(ProductService productService, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.productService = productService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/getProductStatus")
    private ResponseEntity<Page<Products>> getAllProduct(@RequestParam int size,@RequestParam int page,@RequestParam(required = false) Integer status){
        Pageable pageable=PageRequest.of(page,size);

            Page<Products> products = productService.getAllProduct(status, pageable);

        return ResponseEntity.ok(products);
    }
    @GetMapping("/getProductStatusK")
    private ResponseEntity<Page<Products>> getAllProductK(@RequestParam int size,@RequestParam int page,@RequestParam(required = false) Integer status){
        Pageable pageable=PageRequest.of(page,size);

        Page<Products> products = productService.getAllProduct(status, pageable);
        String csvFile = "data.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
             CSVPrinter csvPrinter = new CSVPrinter(writer,
                     CSVFormat.DEFAULT.withHeader("id", "name", "discription"));) {
          List<Products> a= products.get().collect(Collectors.toList());
            for (Products a1:a){
                csvPrinter.printRecord(a1.getId(),a1.getProductName(),a1.getInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(products);
    }
    @GetMapping("/getProductTag")
    private ResponseEntity<Page<Products>> getProductCate(@RequestParam(required = false) String productType,
                                                          @RequestParam(required = false) String provider,
                                                          @RequestParam int size,
                                                          @RequestParam int page,
                                                          @RequestParam(required = false) String tag){
        Pageable pageable=PageRequest.of(page,size);
        Page<Products> products= productService.getProductCate(provider,productType,tag,pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getAllBranch")
    private ResponseEntity<List<String>> getAllBranch(@RequestParam(required = false) String provider
            ,@RequestParam String type
            ,@RequestParam(required = false) String tag
    ){
      List<String> resulf= productService.getAllBranch(tag,provider,type);
        return ResponseEntity.ok(resulf);
    }
    @PutMapping("/updateStatusProduct")
    private ResponseEntity<Products> updateStatus(@RequestParam Long id,@RequestParam int status
    ){
        Products resulf= productService.updateProductStatus(id,status);
        return ResponseEntity.ok(resulf);
    }


    @GetMapping("/getProductById/{id}")
    private ResponseEntity<Products> getProductById(@PathVariable("id") Long id){
        Optional<Products> product= productService.findById(id);
        return ResponseEntity.ok(product.get());
    }
    @PutMapping("/updateProduct")
    private ResponseEntity<?> updateProduct(@RequestBody Products products){
      Optional<Products> optional=  productService.findById(products.getId());
        Products product=null;
      if (!optional.isPresent()){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
        try {
             product = productService.update(products);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
         return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @PostMapping("/createProduct")
    private ResponseEntity<?> creatProduct(@RequestHeader String Authorization , @RequestBody Products products) {
        String email = jwtTokenUtil.getUsernameFromToken(Authorization);
        UserDto userDto = userDetailsService.getUserByEmail(email);
        Products product=new Products();
        if (!userDto.getRole().equals("ROLE_ADMIN")) {
            return ResponseEntity.ok("not admin");
        }
        try {
            product = productService.saveProduct(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }
    @GetMapping("/getAllTypeProduct")
    private ResponseEntity<?> getAllTypeProduct(){
        List<String> stringList=new ArrayList<>(Arrays.asList("Laptop","Diện thoại","Tainghe,Tivi","TB Lưu Trữ"));
        return ResponseEntity.ok(stringList);
    }
    @GetMapping("search")
    private ResponseEntity<List<Products>> searchProductNotExact(@RequestParam String contentSerch){
          List<Products> list = productService.searchByContent(contentSerch);
          return ResponseEntity.ok(list);
    }
    @DeleteMapping("deleteProduct")
    private ResponseEntity<?> deleteProductByid(@RequestParam Long id){
        try {
            productService.deleteProductByid(id);
        }catch (Exception e){
          e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("searchBy")
    private ResponseEntity<List<Products>> searchProductNotExact(@RequestParam String category,
                                                                 @RequestParam String brand,
                                                                 @RequestParam int priceFrom,
                                                                 @RequestParam int priceTo

    ){
        List<Products> list = productService.searchByCategory(category,brand,priceFrom,priceTo);
        return ResponseEntity.ok(list);
    }
}

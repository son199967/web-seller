package son.nguyen.webseller.controller;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import son.nguyen.webseller.config.sercurity.JwtTokenUtil;
import son.nguyen.webseller.dto.UserDto;
import son.nguyen.webseller.model.Prices;
import son.nguyen.webseller.model.ProductDetail;
import son.nguyen.webseller.model.Products;
import son.nguyen.webseller.service.JwtUserDetailsService;
import son.nguyen.webseller.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/craw")
public class CrawController {
    private ProductService productService;
    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsService userDetailsService;
    @Autowired
    public CrawController(ProductService productService, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.productService = productService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/crawler")
    private ResponseEntity<?> crawByUrl( @RequestParam String urlStart,@RequestParam String type,@RequestParam String provied) throws IOException {
        Integer m=0;
        String urlPage ="https://www.anphatpc.com.vn";
        WebClient  webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        HtmlPage myPage = webClient.getPage(urlStart);
        Document doc = Jsoup.parse(myPage.asXml());
        Elements elements = doc.select("div.p-item-2019");

        for (Element a:elements){
            String url = urlPage +  a.select("div.p-container-2019>a").first().attr("href");
            Products products =  productService.getProductHref(url);
            if (products!=null){
                continue;
            }
            String imgProduct = a.select("a.p-img-2019>img").first().attr("src");
            String nameProduct=a.select("a.p-name-2019").first().text();
            String code=a.select("span.p-sku").first().text().replace("Mã SP : ","");
            Long priceNews=0L;
            try {
             priceNews = Long.parseLong(a.select("span.p-price-2019").first().text().
                    replaceAll("\\D+",""));
            }catch (Exception e){
                System.out.println( "not path");
            }
            Long priceOld=0L;
            try {
                priceOld = Long.parseLong(a.select("span.p-old-price-2019").first().text().
                        replaceAll("\\D+", ""));
            }catch (Exception e){
                System.out.println( "not path");
            }
            HtmlPage myPageDetail = webClient.getPage(url);
            Document docDetail = Jsoup.parse(myPageDetail.asXml());
            List<String> contentDetail = docDetail.select("div#detail_summary").first().select("span").stream().map(element -> element.text()).collect(Collectors.toList());
            List<String> imagesDetail = docDetail.select("li.image-title-item").stream().map(element -> element.attr("data-img")).collect(Collectors.toList());
            StringBuffer productInfo=new StringBuffer();


            List<String> discriptionE = docDetail.select("div#product-description>p").stream().map(element -> element.text()).collect(Collectors.toList());

            System.out.println("xx");
            Products products1 =new Products();

            ProductDetail productDetail=new ProductDetail();
            products1.setProductInfo(contentDetail);
            products1.setProductName(nameProduct);
            products1.setCode(code);
            products1.setImageProduct(imgProduct);
            products1.setHref(url);
            products1.setProductType(type);
            products1.setProviderName(provied);
            productDetail.setDiscription(discriptionE);
            productDetail.setImages(imagesDetail);
            productDetail.setDonvi("'");

            productDetail.setColor(new ArrayList<>(Arrays.asList("red","blue","while","black")));
            productDetail.setSize(new ArrayList<>(Arrays.asList(14,15,20)));

            Prices prices =new Prices();
            prices.setUnitPrice(new BigDecimal(priceNews));
            prices.setOldPrice(new BigDecimal(priceOld));

            if (products!=null) {
                products1.setId(products.getId());
                productDetail.setId(products.getProductDetail().getId());
                prices.setId(products.getPrices().getId());
            }

            productService.saveProduct(products1);
            prices.setProducts(products1);
            productDetail.setProducts(products1);
            products1.setProductDetail(productDetail);
            products1.setPrices(prices);
            productService.saveProduct(products1);
            m++;
            if (m>20) return ResponseEntity.ok("oke");
        }
        return ResponseEntity.ok("oke");
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



}

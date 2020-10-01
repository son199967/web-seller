package son.nguyen.webseller.controlner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Mycontrol {
    private static final Logger logger = LoggerFactory.getLogger(Mycontrol.class);
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public List<String> getProducts() {
        List<String> productsList = new ArrayList<>();
        productsList.add("Honey");
        logger.info("oke");
        productsList.add("Almond");
        return productsList;
    }
    @PostMapping(value = "/products")
    public String createProduct(@RequestParam String name) {
        return name;
    }
}

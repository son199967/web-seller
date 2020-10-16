package son.nguyen.webseller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import son.nguyen.webseller.config.sercurity.JwtTokenUtil;
import son.nguyen.webseller.model.Cart;
import son.nguyen.webseller.model.CartItem;
import son.nguyen.webseller.model.User;
import son.nguyen.webseller.service.CartService;
import son.nguyen.webseller.service.JwtUserDetailsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;

    @Autowired
    public CartController(CartService cartService ,JwtTokenUtil jwtTokenUtil,JwtUserDetailsService jwtUserDetailsService) {
        this.cartService = cartService;
        this.jwtTokenUtil=jwtTokenUtil;
        this.userDetailsService=jwtUserDetailsService;
    }
    @GetMapping("/getCartDone")
    private ResponseEntity<List<Cart>> getCartStatusDone(@RequestHeader String Authorization){
        String email = jwtTokenUtil.getUsernameFromToken(Authorization);
        User user =userDetailsService.getUseDaorByEmail(email);
        if (user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        List<Cart> cart = cartService.getCartDone(user.getId());

      return ResponseEntity.ok(cart);
    }
    @GetMapping("/getCartFuture")
    private ResponseEntity<Cart> getCartStatusFurure(@RequestHeader String Authorization){
        if(Authorization==null) return null;
        String email = jwtTokenUtil.getUsernameFromToken(Authorization);
        User user =userDetailsService.getUseDaorByEmail(email);
        if (user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Optional<Cart> cart = cartService.getCartByUserNotPayment(user.getId());
        if (!cart.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cart.get());
    }
    @PostMapping("/createCart")
    private ResponseEntity<Cart> CreatCartAddCartItemToCart(@RequestHeader String Authorization, @RequestBody CartItem cartItem){
        String email = jwtTokenUtil.getUsernameFromToken(Authorization);
        User user =userDetailsService.getUseDaorByEmail(email);
        if (user==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Optional<Cart> cart = cartService.getCartByUserNotPayment(user.getId());
        if (!cart.isPresent()){
           Cart cart1 = cartService.makeCartIteam(user);
            return ResponseEntity.ok(cartService.addCartItemToCart(cart1,cartItem));
        }
        return ResponseEntity.ok(cartService.addCartItemToCart(cart.get(),cartItem));
    }
}

package son.nguyen.webseller.service;

import son.nguyen.webseller.model.Cart;
import son.nguyen.webseller.model.CartItem;
import son.nguyen.webseller.model.User;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Optional<Cart> getCartByUserNotPayment(Long id);
    Cart addCartItemToCart( Cart cart ,CartItem cartItems);
    Cart makeCartIteam(User user);
    List<Cart> getCartDone(Long id);
    List<Cart> getCartPROCESS(Long id);


}

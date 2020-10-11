package son.nguyen.webseller.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import son.nguyen.webseller.config.CART;
import son.nguyen.webseller.model.Cart;
import son.nguyen.webseller.model.CartItem;
import son.nguyen.webseller.model.User;
import son.nguyen.webseller.repository.CartRepository;
import son.nguyen.webseller.service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl   implements CartService {
    private CartRepository cartRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> getCartByUserNotPayment(Long id) {
        Optional<Cart> cart =cartRepository.getByUserAndStatus(id, CART.NOTPAYMENT.ordinal());
        return cart;
    }

    @Override
    public Cart addCartItemToCart(Cart cart,CartItem items) {
        items.setCart(cart);
        List<CartItem> cartItems1= cart.getCartItems();
        if (cartItems1==null||cartItems1.size()==0){
            cartItems1 =new ArrayList<>();
            cartItems1.add(items);
            cart.setCartItems(cartItems1);
            return cart;
        }
        Integer vitri=null;
      for (int i=0;i<cartItems1.size();i++){
          if (cartItems1.get(i).getProductId()==items.getProductId()){
              vitri=i;
          }
      }
      if (vitri!=null){
          cartItems1.remove(vitri);

      }
      cartItems1.add(items);
        cart.setCartItems(cartItems1);
        return cart;
    }

    @Override
    public Cart makeCartIteam(User user) {
      Cart cart =new Cart();
      cart.setUser(user);
      cart.setStatus(CART.NOTPAYMENT.ordinal());
      cart.setCartItems(new ArrayList<>());
      return cart;
    }


}

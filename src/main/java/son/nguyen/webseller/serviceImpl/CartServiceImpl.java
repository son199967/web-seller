package son.nguyen.webseller.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import son.nguyen.webseller.config.CART;
import son.nguyen.webseller.model.Cart;
import son.nguyen.webseller.model.CartItem;
import son.nguyen.webseller.model.Products;
import son.nguyen.webseller.model.User;
import son.nguyen.webseller.repository.CartItemRepository;
import son.nguyen.webseller.repository.CartRepository;
import son.nguyen.webseller.repository.ProductRepository;
import son.nguyen.webseller.service.CartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl   implements CartService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
       @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Cart> getCartByUserNotPayment(Long id) {

        Optional<Cart> cart =cartRepository.getCarStatus(id, CART.NOTPAYMENT.ordinal());
        return cart;
    }

    @Override
    public Cart addCartItemToCart(Cart cart,CartItem items) {
        items.setCart(cart);
        Products products = productRepository.findById(items.getProductId()).get();
        items.setPrice(products.getPrices().get(0).getUnitPrice());
        items.setTotalPriceItem(items.getPrice().multiply(new BigDecimal(items.getAmount())));
        items.setNameProduct(products.getProductName());
        items.setImage(products.getImageProduct());
        List<CartItem> cartItems1= cart.getCartItems();
        if (cartItems1==null||cartItems1.size()==0){
            cartItems1 =new ArrayList<>();
            cartItems1.add(items);
            cart.setCartItems(cartItems1);
            cart.setTotalPrice(totalMoneyCount(cart));
            cartRepository.save(cart);
            return cart;
        }
        Integer vitri=null;
      for (int i=0;i<cartItems1.size();i++){
          if (cartItems1.get(i).getProductId()==items.getProductId()){
              vitri=i;
          }
      }
      if (vitri!=null){
          cartItemRepository.deleteByIdSQl(cartItems1.get(vitri).getId());
          cartItems1.remove(cartItems1.get(vitri));
      }
        cartItems1.add(items);
        cart.setCartItems(cartItems1);
        cart.setTotalPrice(totalMoneyCount(cart));

        cartRepository.save(cart);
        return cart;
    }
    private BigDecimal totalMoneyCount(Cart cart){
           List<CartItem> cartItem= cart.getCartItems();
           BigDecimal total= new BigDecimal(0);
           for (CartItem c:cartItem){

               total=total.add(c.getTotalPriceItem());
           }
           return total;
    }

    @Override
    public Cart makeCartIteam(User user) {
      Cart cart =new Cart();
      cart.setUser(user);
      cart.setStatus(CART.NOTPAYMENT.ordinal());
      cart.setCartItems(new ArrayList<>());
      return cart;
    }

    @Override
    public List<Cart> getCartPROCESS(Long id) {
        List<Cart> cart =cartRepository.getListCarStatus(id, CART.PROCESS.ordinal());
        return cart;
    }
    @Override
    public List<Cart> getCartDone(Long id) {
        List<Cart> cart =cartRepository.getListCarStatus(id, CART.PAYMENT.ordinal());
        return cart;
    }
}

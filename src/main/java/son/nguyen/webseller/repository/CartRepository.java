package son.nguyen.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.nguyen.webseller.model.Cart;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> getByUserAndStatus(Long id,int status);
}

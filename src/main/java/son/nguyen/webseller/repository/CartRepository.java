package son.nguyen.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import son.nguyen.webseller.model.Cart;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query("select a from Cart  a where a.user.id=?1 and a.status=?2")
    Optional<Cart> getCarStatus(Long id,int status);
    @Query("select a from Cart  a where a.user.id=?1 and a.status=?2")
    List<Cart> getListCarStatus(Long id, int status);
}

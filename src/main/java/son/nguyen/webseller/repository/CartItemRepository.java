package son.nguyen.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import son.nguyen.webseller.model.CartItem;

import javax.transaction.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    @Transactional
    @Modifying
    @Query("delete from CartItem a where a.id=?1")
    void deleteByIdSQl(Long id);

}

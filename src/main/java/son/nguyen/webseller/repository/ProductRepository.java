package son.nguyen.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import son.nguyen.webseller.model.Products;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
  Optional<Products> findByProductName(String name);

  @Query("select a from Products a")
  List<Products> getAllProduct();
}

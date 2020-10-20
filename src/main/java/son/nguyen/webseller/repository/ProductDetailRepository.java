package son.nguyen.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import son.nguyen.webseller.model.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {

}

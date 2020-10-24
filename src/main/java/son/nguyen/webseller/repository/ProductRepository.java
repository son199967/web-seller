package son.nguyen.webseller.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  Page<Products> getAllProduct(Pageable pageable);
  @Query("select a from Products a where a.status=?1")
  Page<Products> getAllProductStatus(int status,Pageable pageable);
  @Query("select a.providerName from Products a where a.productType=?1 and a.providerName=?2")
  List<String> getAllBranch(String productType,String provider);
  @Query("select a.providerName from Products a where a.productType=?1 and a.tags=?2")
  List<String> getAllBranchTag(String productType,String tag);
  @Query("select a.providerName from Products a where a.productType=?1 and a.providerName=?2")
  List<String> getAllTypeProvide(String productType,String provide);
  @Query("select a from Products  a where a.productName like ?1")
  List<Products> searchProduct(String content);
  @Query("select a from Products a where  a.productType=?1 and a.providerName=?2")
  List<Products> searcgProductBy1(String category,String brand,int priceFrom,int priceTo);
  @Query("select a from Products a where  a.productType=?1")
  List<Products> searcgProductBy2(String category,int priceFrom,int priceTo);
  @Query("select a.productType from Products a")
  List<String> getAllTypeProduct();
  @Query("select a from Products  a where a.href = ?1")
  Optional<Products> getByhref(String url);
  @Query("select a from Products  a where a.id = ?1")
  Optional<Products> getById(Long id);

  @Query("select a from Products a where a.tags=?1")
  Page<Products> getProductByTag(String tag,Pageable pageable);
  @Query("select a from Products a where a.tags=?2 and a.providerName=?1")
  Page<Products> getProductTagProvide(String provide,String tag,Pageable pageable);
  @Query("select a from Products a where a.tags=?2 and a.productType=?1")
  Page<Products> getProductTagProvidType(String productType,String tag,Pageable pageable);
  @Query("select a from Products a where a.tags=?2 and a.productType=?1 and a.providerName=?3")
  Page<Products> getProductTagProvidType(String productType,String tag,String provide,Pageable pageable);

  @Query("select a from Products a where   a.productType=?1 and a.providerName=?2")
  Page<Products> getProductProvidType(String productType,String provide,Pageable pageable);



}

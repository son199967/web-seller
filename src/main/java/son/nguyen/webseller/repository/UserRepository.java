package son.nguyen.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import son.nguyen.webseller.model.UserDao;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Integer> {
    UserDao findByEmail(String email);
}

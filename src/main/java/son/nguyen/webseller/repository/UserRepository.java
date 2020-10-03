package son.nguyen.webseller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import son.nguyen.webseller.model.UserDao;

public interface UserRepository extends JpaRepository<UserDao, Integer> {
    UserDao findByEmail(String email);
}

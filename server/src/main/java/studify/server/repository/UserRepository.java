package studify.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studify.server.commons.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(long userId);
}
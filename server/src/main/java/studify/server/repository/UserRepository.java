package studify.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import studify.server.entities.AppUser;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {
    AppUser findAppUserByUsername(String username);

    @Query(value = "SELECT * FROM APP_USER", nativeQuery = true)
    List<AppUser> findAll();
}
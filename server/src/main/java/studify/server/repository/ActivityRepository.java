package studify.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import studify.server.entities.Activity;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Activity findActivityById(long id);

    @Query(value = "SELECT * FROM ACTIVITY", nativeQuery = true)
    List<Activity> findAll();
}
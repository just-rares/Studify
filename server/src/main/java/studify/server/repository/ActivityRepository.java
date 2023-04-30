package studify.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studify.server.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Activity findActivityById(long activityId);
}

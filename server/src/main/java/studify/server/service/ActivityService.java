package studify.server.service;

import org.springframework.stereotype.Service;
import studify.server.entities.Activity;
import studify.server.repository.ActivityRepository;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activities;

    public ActivityService(ActivityRepository activities) {
        this.activities = activities;
    }

    public int save(Activity activity) {
        try {
            if (activity == null) {
                throw new IllegalArgumentException("Activity cannot be null");
            }

            activities.save(activity);
            return 1; // Successful save
        }
        catch (IllegalArgumentException e) {
            return -1; // Failed save due to null activity
        }
        catch (Exception e) {
            return -2; // Failed save due to other exception
        }
    }

    public Activity getActivityById(long id) {
        if(id < 0 || !activities.existsById(id)) {
            return null;
        }
        return activities.findActivityById(id);
    }

    public List<Activity> getAllActivities() {
        return activities.findAll();
    }

}

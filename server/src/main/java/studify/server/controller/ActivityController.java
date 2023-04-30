package studify.server.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studify.server.entities.Activity;
import studify.server.entities.AppUser;
import studify.server.service.ActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/{activity}")
    public ResponseEntity<Activity> getById(@PathVariable("activityId") Long activityId) {
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }

    @PostMapping("/new")
    public ResponseEntity<String> newActivity(@RequestBody Activity activity) {
        if (activity == null) {
            return ResponseEntity.badRequest().body("Invalid activity data");
        }

        int result = activityService.save(activity);

        if (result != 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save activity");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Activity added: " + activity.toString());
    }

    @PostMapping("/new/{title}")
    public ResponseEntity<String> newUser(@PathVariable("title") String title) {
        Activity activity = new Activity(title == null ? "title" : title);

        int result = activityService.save(activity);

        if(result != 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save activity");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(activity.toString());
    }

}

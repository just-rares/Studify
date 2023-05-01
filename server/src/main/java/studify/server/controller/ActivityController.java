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

    /*
           _____ ______ _______
          / ____|  ____|__   __|
         | |  __| |__     | |
         | | |_ |  __|    | |
         | |__| | |____   | |
          \_____|______|  |_|
    */
    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getById(@PathVariable("activityId") Long activityId) {
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }

    /*
          _____   ____   _____ _______
         |  __ \ / __ \ / ____|__   __|
         | |__) | |  | | (___    | |
         |  ___/| |  | |\___ \   | |
         | |    | |__| |____) |  | |
         |_|     \____/|_____/   |_|
    */

    @PostMapping(path = {"", "/"})
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
    public ResponseEntity<String> newActivity(@PathVariable("title") String title) {
        if(title == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Title can not be null");
        }
        Activity activity = new Activity(title);

        int result = activityService.save(activity);

        if(result != 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save activity");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Activity created: " + activity.toString());
    }

    /*
         _____  _    _ _______
        |  __ \| |  | |__   __|
        | |__) | |  | |  | |
        |  ___/| |  | |  | |
        | |    | |__| |  | |
        |_|     \____/   |_|
*/

}
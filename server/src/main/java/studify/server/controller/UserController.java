package studify.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import studify.server.entities.AppUser;
import org.springframework.http.ResponseEntity;
import studify.server.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Creates the instance and activates the endpoints.
     * Connects to database through userService
     *
     * @param userService Service that handles repository of users
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Endpoint for retrieving all the users in the repository.
     *
     * @return List of users
     */
    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * GET endpoint for returning a user by their id.
     *
     * @param username String value of the username
     * @return AppUser instance with specified id.
     *
     */
    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getById(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    /**
     * POST Endpoint for adding a user to the repository
     *
     * @param appUser Instance of the AppUser given in the body of the request.
     * @return Response of the server upon adding the entity
     */
    @PostMapping(path = { "", "/" })
    public ResponseEntity<String> add(@RequestBody AppUser appUser) {
        System.out.println(appUser);
        int result = userService.save(appUser);
        // Null Error
        if(result == -1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save user. This may be because of a null value");
        }
        //Server Error
        if(result == -2) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save user. Unexpected Error");
        }
        //Success
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User added: " + appUser.toString());
    }

    @PostMapping("/new/{username}")
    public ResponseEntity<String> newUser(@PathVariable("username") String username) {
        if(username == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Username can not be null");
        }
        AppUser appUser = new AppUser(username);
        int result = userService.save(appUser);
        // Null Error
        if(result == -1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save user. This may be because of a null value");
        }
        //Server Error
        if(result == -2) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save user. Unexpected Error");
        }
        //Success
        return ResponseEntity.status(HttpStatus.CREATED).body("User added: " + appUser.toString());
    }

//    @PutMapping("/edit")
//    public ResponseEntity<String> editUser(@RequestBody AppUser appUser) {
//        //TODO Check for errors just like above.
//        int res = userService.editUser(appUser);
//
//        return ResponseEntity.ok().body("User Saved: " + appUser.toString());
//    }
}
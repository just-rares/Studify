package studify.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
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
    public ResponseEntity<AppUser> add(@RequestBody AppUser appUser) {
        System.out.println(appUser);
        int result = userService.save(appUser);
        return handleResult(result, appUser);
    }

    private ResponseEntity<AppUser> handleResult(int result, AppUser appUser) {
        // Null Error
        if(result == -1) {
            System.out.println("Failed to save user. This may be because of a null value");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        //Server Error
        if(result == -2) {
            System.out.println("Failed to save user. Unexpected Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        //Success
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appUser);
    }

    @PostMapping("/new/{username}")
    public ResponseEntity<AppUser> newUser(@PathVariable("username") String username) {
        if(username == null) {
            System.out.println("Username must not be null.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        AppUser appUser = new AppUser(username);
        int result = userService.save(appUser);
        return handleResult(result, appUser);
    }
    @MessageMapping("/{username}/users/add")
    @SendTo("/topic/users/add")
    public AppUser handleUserAdd(@Payload Object payload) {
        if (payload instanceof AppUser user) {
            var appUserResponseEntity = add(user);
            return appUserResponseEntity.getBody();
        } else if (payload instanceof String newUsername) {
            var appUserResponseEntity = newUser(newUsername);
            return appUserResponseEntity.getBody();
        } else {
            // Handle unsupported payload type
            throw new IllegalArgumentException("Unsupported payload type");
        }
    }

}
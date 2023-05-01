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

    public UserController(UserService userService) {
        this.userService = userService;
    }

/*
       _____ ______ _______
      / ____|  ____|__   __|
     | |  __| |__     | |
     | | |_ |  __|    | |
     | |__| | |____   | |
      \_____|______|  |_|
*/
    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

/*
      _____   ____   _____ _______
     |  __ \ / __ \ / ____|__   __|
     | |__) | |  | | (___    | |
     |  ___/| |  | |\___ \   | |
     | |    | |__| |____) |  | |
     |_|     \____/|_____/   |_|
*/

    @PostMapping(path = { "", "/" })
    public ResponseEntity<String> add(@RequestBody AppUser appUser) {
        System.out.println(appUser);
        int result = userService.save(appUser);
        if (result != 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save user");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User added: " + appUser.toString());
    }

    @PostMapping("/new/{username}")
    public ResponseEntity<String> newUser(@PathVariable("username") String username) {
        if(username == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Username can not be null");
        }
        //TODO : Check if username already exists
        AppUser appUser = new AppUser(username);
        int result = userService.save(appUser);
        if(result != 1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save user");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("User added: " + appUser.toString());
    }
/*
         _____  _    _ _______
        |  __ \| |  | |__   __|
        | |__) | |  | |  | |
        |  ___/| |  | |  | |
        | |    | |__| |  | |
        |_|     \____/   |_|
*/

    @PutMapping("/edit")
    public ResponseEntity<String> editUser(@RequestBody AppUser appUser) {
        //TODO Check for errors just like above.
        int res = userService.editUser(appUser);

        return ResponseEntity.ok().body("User Saved: " + appUser.toString());
    }
}
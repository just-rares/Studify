package studify.server.controller;

import org.springframework.web.bind.annotation.*;
import studify.server.entities.AppUser;
import org.springframework.http.ResponseEntity;
import studify.server.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/existing")
    public ResponseEntity<String> add(@RequestBody AppUser appUser) {
        int res = userService.save(appUser);
//        if(res == -1) {
//            return ResponseEntity.badRequest().body("Invalid Attributes");
//        }
        return ResponseEntity.ok().body("User added: " + appUser.toString());
    }

    @PostMapping("/new/{username}")
    public ResponseEntity<String> newUser(@PathVariable("username") String username) {
        AppUser user = new AppUser(username == null ? "username" : username);
        int res = userService.save(user);
//        if(res == -1) {
//            return ResponseEntity.badRequest().body("Invalid Attributes");
//        }
        return ResponseEntity.ok().body(user.toString());
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editUser(@RequestBody AppUser appUser) {
        int res = userService.editUser(appUser);
//        if(res == -1) {
//            return ResponseEntity.badRequest().body("Invalid id");
//        }
//        if(res == -2) {
//            return ResponseEntity.badRequest().body("Invalid attributes");
//        }
        return ResponseEntity.ok().body("User Saved: " + appUser.toString());
    }
}
package studify.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/testConnection")
    public ResponseEntity<String> testController() {
        System.out.println("Connection Successful");
        return ResponseEntity.ok("Connection is successful");
    }
}
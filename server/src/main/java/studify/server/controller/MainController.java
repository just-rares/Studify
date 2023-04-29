package studify.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/test")
    public ResponseEntity<String> testController() {
        return ResponseEntity.ok("Test response");
    }
}

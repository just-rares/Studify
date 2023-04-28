package server.controller;

import commons.Activity;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getById(@PathVariable("id") Long id) {
        return null;
    }

}

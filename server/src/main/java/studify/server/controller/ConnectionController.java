package studify.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/connect")
public class ConnectionController {
    /**
     * Checks if client is connected to the correct server url
     * @return 200 response
     */
    @GetMapping()
    public ResponseEntity connect() {
        return ResponseEntity.ok().build();
    }
}

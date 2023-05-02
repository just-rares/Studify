package studify.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "studify.server")
public class MainServer {

    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }

}
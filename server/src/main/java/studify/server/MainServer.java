package studify.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import studify.server.repository.UserRepository;

@SpringBootApplication(scanBasePackages = "studify.server")
public class MainServer {

    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }

}

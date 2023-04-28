package server.service;

import org.springframework.stereotype.Service;
import server.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }
}

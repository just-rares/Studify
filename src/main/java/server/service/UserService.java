package server.service;

import commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

//    public UserService(UserRepository users) {
//        this.users = users;
//    }

    public User getUserById(long id) {
        if(id < 0 || !users.existsById(id)) {
            return null;
        }
        return users.findById(id).get();
    }
}

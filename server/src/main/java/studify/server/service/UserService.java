package studify.server.service;

import org.springframework.stereotype.Service;
import studify.server.entities.AppUser;
import studify.server.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public AppUser getUserById(long id) {
        if(id < 0 || !users.existsById(id)) {
            return null;
        }
        return users.findUserById(id);
    }

    public void save(AppUser user) {
        users.save(user);
    }
}
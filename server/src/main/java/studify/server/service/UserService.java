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

    public int save(AppUser appUser) {
        //TODO: Having a null check here always fails for some reason.
        users.save(appUser);
        return 1;
    }

    public int editUser(AppUser user) {
//        if(user.id == null || !users.existsById(user.id)) {
//            return -1;
//        }
//        if(user.username == null || user.experience < 0 || user.level < 0) {
//            return -2;
//        }
        users.save(user);
        return 1;
    }
}
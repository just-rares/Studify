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
        try {
            if (appUser == null) {
                throw new IllegalArgumentException("User cannot be null");
            }

            users.save(appUser);
            return 1; // Successful save
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Log the error or perform other actions
            return -1; // Failed save due to null activity
        } catch (Exception e) {
            e.printStackTrace(); // Log the error or perform other actions
            return -2; // Failed save due to other exception
        }
    }

    public int editUser(AppUser user) {
        // TODO : Check if exception
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
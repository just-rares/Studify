package studify.server.commons;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class AppUser {
    @Id
    public Long id;
    public String username;
    public Integer experience;
    public Integer level;


    public User() {

    }

    public User(String username) {
        this.experience = 0;
        this.level = 0;
        this.username = username;
    }


}
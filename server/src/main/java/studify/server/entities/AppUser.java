package studify.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class AppUser {
    @Id
    public String username;
    public Integer experience;
    public Integer level;


    public AppUser() {

    }

    public AppUser(String username) {
        this.experience = 0;
        this.level = 0;
        this.username = username;
    }

    @Override
    public String toString() {
        return "AppUser{\n" +
                "\nusername: '" + username + '\'' +
                "\nexperience: " + experience +
                "\nlevel: " + level +
                "\n}";
    }
}
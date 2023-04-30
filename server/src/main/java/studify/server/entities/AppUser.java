package studify.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
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
                "id: " + id +
                "\nusername: '" + username + '\'' +
                "\nexperience: " + experience +
                "\nlevel: " + level +
                "\n}";
    }
}
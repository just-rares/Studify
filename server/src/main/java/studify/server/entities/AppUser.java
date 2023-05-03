package studify.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

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
        return "AppUser{" +
                "\nusername: '" + username + '\'' +
                "\nexperience: " + experience +
                "\nlevel: " + level +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
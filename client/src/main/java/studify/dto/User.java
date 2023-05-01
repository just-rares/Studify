package studify.dto;

public class User {
    public String username;
    public Integer experience;
    public Integer level;

    public User() {

    }

    public User(String username) {
        this.username = username;
        this.experience = 0;
        this.level = 0;
    }

    public User(String username, Integer experience, Integer level) {
        this.username = username;
        this.experience = experience;
        this.level = level;
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

package studify.dto;

public class User {
    private String username;
    private Integer experience;
    private Integer level;

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
}

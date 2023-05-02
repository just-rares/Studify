package studify.dto;

public class User {
    public String username;
    public Integer experience;
    public Integer level;

    /**
     * Empty constructor for OkHttp to construct objects
     * from JSON
     */
    public User() {

    }

    /**
     * Create a new User with default stats
     *
     * @param username String representing the name
     */
    public User(String username) {
        this.username = username;
        this.experience = 0;
        this.level = 0;
    }

    /**
     * Creates an already existing / determined value user
     *
     * @param username string representing the name
     * @param experience int value of the experience
     * @param level int value of the level
     */
    public User(String username, Integer experience, Integer level) {
        this.username = username;
        this.experience = experience;
        this.level = level;
    }

    /**
     * Returns a human friendly representation of the user
     *
     * @return String representation of the User
     */
    @Override
    public String toString() {
        return "AppUser{\n" +
                "\nusername: '" + username + '\'' +
                "\nexperience: " + experience +
                "\nlevel: " + level +
                "\n}";
    }
}

package studify.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    public String title;

    public Integer hours;

    public Activity() {
    }

    public Activity(String title) {
        this.title = title;
        this.hours = 0;
    }

    @Override
    public String toString() {
        return "Activity{\n" +
            "id: " + id +
            "\ntitle: '" + title + '\'' +
            "\nhours spent: " + hours +
            "\n}";
    }
}

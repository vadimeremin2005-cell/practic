package app.person.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class User {
    private int id;
    private String firstname;
    private String surname;
    private String lastname;
    private LocalDate birthday;
    private String location;

    public User(Person person) {
        this.id = person.getId();
        this.firstname = person.getFirstname();
        this.surname = person.getSurname();
        this.lastname = person.getLastname();
        this.birthday = person.getBirthday();
        this.location = person.getLocation();
    }
}

package app.person.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    @Id
    private int id;
    private String firstname;
    private String surname;
    private String lastname;
    private String birthday;
}

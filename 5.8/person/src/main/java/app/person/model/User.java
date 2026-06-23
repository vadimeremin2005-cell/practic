package app.person.model;

import java.time.LocalDate;

public class User {
    private int id;
    private String firstname;
    private String surname;
    private String lastname;
    private LocalDate birthday;
    private String location;

    public User() {
    }

    public User(Person person) {
        this.id = person.getId();
        this.firstname = person.getFirstname();
        this.surname = person.getSurname();
        this.lastname = person.getLastname();
        this.birthday = person.getBirthday();
        this.location = person.getLocation();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

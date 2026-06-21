package app.controller;

import app.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final List<Person> persons = new ArrayList<>();
    private int nextId = 1;

    @GetMapping
    public List<Person> getAll() {
        return persons;
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable int id) {
        return findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        person.setId(nextId++);
        persons.add(person);
        return person;
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable int id, @RequestBody Person person) {
        Person existing = findById(id);
        existing.setFirstname(person.getFirstname());
        existing.setSurname(person.getSurname());
        existing.setLastname(person.getLastname());
        existing.setBirthday(person.getBirthday());
        return existing;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        persons.removeIf(person -> person.getId() == id);
    }

    private Person findById(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

package app.person.controller;

import app.person.model.Person;
import app.person.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;
    private final RestTemplate restTemplate;

    public PersonController(PersonService personService, RestTemplate restTemplate) {
        this.personService = personService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/person")
    public List<Person> getAll() {
        RestTemplate restTemplate = this.restTemplate;
        return personService.findAll();
    }

    @GetMapping("/person/{id}")
    public Person getById(@PathVariable int id) {
        return personService.findById(id);
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @PutMapping("/person/{id}")
    public Person update(@PathVariable int id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        personService.delete(id);
    }
}

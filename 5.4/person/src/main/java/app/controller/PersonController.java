package app.controller;

import app.model.Person;
import app.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Iterable<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable int id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable int id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        personService.delete(id);
    }
}

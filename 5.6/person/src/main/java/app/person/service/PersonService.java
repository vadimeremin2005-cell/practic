package app.person.service;

import app.person.model.Person;
import app.person.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person update(int id, Person person) {
        if (!personRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        personRepository.deleteById(id);
        person.setId(id);
        return personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}

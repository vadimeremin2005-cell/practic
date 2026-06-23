package app.person.service;

import app.person.model.Person;
import app.person.model.Weather;
import app.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;
    private final String locationServiceUrl;

    public PersonService(PersonRepository personRepository,
                         RestTemplate restTemplate,
                         @Value("${location.service.url}") String locationServiceUrl) {
        this.personRepository = personRepository;
        this.restTemplate = restTemplate;
        this.locationServiceUrl = locationServiceUrl;
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

    public Weather getWeather(int id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return restTemplate.getForObject(
                locationServiceUrl + "?name={name}",
                Weather.class,
                person.getLocation()
        );
    }
}

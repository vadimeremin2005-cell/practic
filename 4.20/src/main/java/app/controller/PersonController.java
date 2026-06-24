package app.controller;

import app.dto.Message;
import app.dto.Person;
import app.service.MessageService;
import app.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final MessageService messageService;

    public PersonController(PersonService personService, MessageService messageService) {
        this.personService = personService;
        this.messageService = messageService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable int id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
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

    @GetMapping("/{p_id}/message")
    public List<Message> getAllByPerson(@PathVariable("p_id") int personId) {
        return messageService.findByPersonId(personId);
    }

    @GetMapping("/{p_id}/message/{m_id}")
    public Message getByPersonAndId(@PathVariable("p_id") int personId, @PathVariable("m_id") int messageId) {
        return messageService.findByPersonAndId(personId, messageId);
    }

    @PostMapping("/{p_id}/message")
    @ResponseStatus(HttpStatus.CREATED)
    public Message createForPerson(@PathVariable("p_id") int personId, @RequestBody Message message) {
        return messageService.createForPerson(personId, message);
    }

    @DeleteMapping("/{p_id}/message/{m_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteForPerson(@PathVariable("p_id") int personId, @PathVariable("m_id") int messageId) {
        messageService.deleteForPerson(personId, messageId);
    }
}

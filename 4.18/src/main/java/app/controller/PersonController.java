package app.controller;

import app.dto.Message;
import app.dto.Person;
import app.service.MessageService;
import app.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;
    private final MessageService messageService;

    public PersonController(PersonService personService, MessageService messageService) {
        this.personService = personService;
        this.messageService = messageService;
    }

    @GetMapping("/person")
    public List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping("/person/{id}")
    public Person getById(@PathVariable int id) {
        return personService.findById(id);
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        return personService.create(person);
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

    @GetMapping("/message")
    public List<Message> getAllMessages() {
        return messageService.findAll();
    }

    @GetMapping("/message/{id}")
    public Message getMessageById(@PathVariable int id) {
        return messageService.findById(id);
    }

    @PostMapping("/message")
    @ResponseStatus(HttpStatus.CREATED)
    public Message createMessage(@RequestBody Message message) {
        return messageService.create(message);
    }

    @PutMapping("/message/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        return messageService.update(id, message);
    }

    @DeleteMapping("/message/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable int id) {
        messageService.delete(id);
    }

    @GetMapping("/person/{p_id}/message")
    public List<Message> getAllByPerson(@PathVariable("p_id") int personId) {
        return messageService.findAllByPersonId(personId);
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public Message getByPersonAndId(@PathVariable("p_id") int personId, @PathVariable("m_id") int messageId) {
        return messageService.findByPersonIdAndMessageId(personId, messageId);
    }

    @PostMapping("/person/{p_id}/message")
    @ResponseStatus(HttpStatus.CREATED)
    public Message createForPerson(@PathVariable("p_id") int personId, @RequestBody Message message) {
        return messageService.createForPerson(personId, message);
    }

    @DeleteMapping("/person/{p_id}/message/{m_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteForPerson(@PathVariable("p_id") int personId, @PathVariable("m_id") int messageId) {
        messageService.deleteForPerson(personId, messageId);
    }
}

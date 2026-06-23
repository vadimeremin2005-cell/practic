package app.controller;

import app.dto.Message;
import app.dto.Person;
import app.repository.MessageRepository;
import app.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PersonController {
    private final PersonRepository personRepository;
    private final MessageRepository messageRepository;

    public PersonController(PersonRepository personRepository, MessageRepository messageRepository) {
        this.personRepository = personRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/person")
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public Person getById(@PathVariable int id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/person/{id}")
    public Person update(@PathVariable int id, @RequestBody Person person) {
        if (!personRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        personRepository.deleteById(id);
        person.setId(id);
        return personRepository.save(person);
    }

    @DeleteMapping("/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        personRepository.deleteById(id);
    }

    @GetMapping("/message")
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/message/{id}")
    public Message getMessageById(@PathVariable int id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/message")
    @ResponseStatus(HttpStatus.CREATED)
    public Message createMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @PutMapping("/message/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        if (!messageRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        messageRepository.deleteById(id);
        message.setId(id);
        return messageRepository.save(message);
    }

    @DeleteMapping("/message/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable int id) {
        messageRepository.deleteById(id);
    }

    @GetMapping("/person/{p_id}/message")
    public List<Message> getAllByPerson(@PathVariable("p_id") int personId) {
        if (!personRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return messageRepository.findByPerson_Id(personId);
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public Message getByPersonAndId(@PathVariable("p_id") int personId, @PathVariable("m_id") int messageId) {
        if (!personRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return messageRepository.findByIdAndPerson_Id(messageId, personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/person/{p_id}/message")
    @ResponseStatus(HttpStatus.CREATED)
    public Message createForPerson(@PathVariable("p_id") int personId, @RequestBody Message message) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        message.setPerson(person);
        return messageRepository.save(message);
    }

    @DeleteMapping("/person/{p_id}/message/{m_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteForPerson(@PathVariable("p_id") int personId, @PathVariable("m_id") int messageId) {
        if (!personRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Message message = messageRepository.findByIdAndPerson_Id(messageId, personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        messageRepository.delete(message);
    }
}

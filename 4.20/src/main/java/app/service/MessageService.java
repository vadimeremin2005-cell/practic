package app.service;

import app.dto.Message;
import app.dto.Person;
import app.repository.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final PersonService personService;

    public MessageService(MessageRepository messageRepository, PersonService personService) {
        this.messageRepository = messageRepository;
        this.personService = personService;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findById(int id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public Message update(int id, Message message) {
        if (!messageRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        messageRepository.deleteById(id);
        message.setId(id);
        return messageRepository.save(message);
    }

    public void delete(int id) {
        messageRepository.deleteById(id);
    }

    public List<Message> findByPersonId(int personId) {
        personService.requireExists(personId);
        return messageRepository.findByPerson_Id(personId);
    }

    public Message findByPersonAndId(int personId, int messageId) {
        personService.requireExists(personId);
        return messageRepository.findByIdAndPerson_Id(messageId, personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Message createForPerson(int personId, Message message) {
        Person person = personService.getRequired(personId);
        message.setPerson(person);
        return messageRepository.save(message);
    }

    public void deleteForPerson(int personId, int messageId) {
        personService.requireExists(personId);
        Message message = messageRepository.findByIdAndPerson_Id(messageId, personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        messageRepository.delete(message);
    }
}

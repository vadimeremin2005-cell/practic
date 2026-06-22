package app.service;

import app.dto.Message;
import app.dto.Person;
import app.repository.MessageRepository;
import app.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final PersonRepository personRepository;

    public MessageService(MessageRepository messageRepository, PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findById(int id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Message create(Message message) {
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

    public List<Message> findAllByPersonId(int personId) {
        if (!personRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return messageRepository.findByPerson_Id(personId);
    }

    public Message findByPersonIdAndMessageId(int personId, int messageId) {
        if (!personRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return messageRepository.findByIdAndPerson_Id(messageId, personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Message createForPerson(int personId, Message message) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        message.setPerson(person);
        return messageRepository.save(message);
    }

    public void deleteForPerson(int personId, int messageId) {
        if (!personRepository.existsById(personId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Message message = messageRepository.findByIdAndPerson_Id(messageId, personId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        messageRepository.delete(message);
    }
}

package app.controller;

import app.dto.Message;
import app.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public List<Message> getAll() {
        return messageService.findAll();
    }

    @GetMapping("/message/{id}")
    public Message getById(@PathVariable int id) {
        return messageService.findById(id);
    }

    @PostMapping("/message")
    @ResponseStatus(HttpStatus.CREATED)
    public Message create(@RequestBody Message message) {
        return messageService.create(message);
    }

    @PutMapping("/message/{id}")
    public Message update(@PathVariable int id, @RequestBody Message message) {
        return messageService.update(id, message);
    }

    @DeleteMapping("/message/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
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

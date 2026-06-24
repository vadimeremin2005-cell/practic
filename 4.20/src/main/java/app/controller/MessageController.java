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
        return messageService.save(message);
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
}

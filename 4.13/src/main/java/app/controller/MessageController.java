package app.controller;

import app.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final List<Message> messages = new ArrayList<>();
    private int nextId = 1;

    @GetMapping
    public List<Message> getAll() {
        return messages;
    }

    @GetMapping("/{id}")
    public Message getById(@PathVariable int id) {
        return findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message create(@RequestBody Message message) {
        message.setId(nextId++);
        messages.add(message);
        return message;
    }

    @PutMapping("/{id}")
    public Message update(@PathVariable int id, @RequestBody Message message) {
        Message existing = findById(id);
        existing.setTitle(message.getTitle());
        existing.setText(message.getText());
        existing.setTime(message.getTime());
        return existing;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        messages.removeIf(message -> message.getId() == id);
    }

    private Message findById(int id) {
        return messages.stream()
                .filter(message -> message.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

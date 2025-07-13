package com.k8s.observebility.wa1.pgl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k8s.observebility.wa1.pgl.entity.Message;
import com.k8s.observebility.wa1.pgl.repository.MessageRepository;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository repository;

    private final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @GetMapping("/getall")
    public List<Message> getAll() {
        logger.info("GET /api/messages/getall called");
        List<Message> all = repository.findAll();
        all.forEach(m -> logger.info(String.format("Message: %s", m.toString())));
        return all;
    }

    @PostMapping("/create")
    public Message create(@RequestBody Message message) {
        logger.info(String.format("POST /api/messages/create: %s", message.getContent()));
        return repository.save(message);
    }

    @PutMapping("/{id}")
    public Message update(@PathVariable Long id, @RequestBody Message newMessage) {
        logger.info(String.format("PUT /api/messages/%d called", id));
        return repository.findById(id).map(m -> {
            m.setContent(newMessage.getContent());
            return repository.save(m);
        }).orElseGet(() -> {
            newMessage.setId(id);
            return repository.save(newMessage);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logger.info(String.format("DELETE /api/messages/%d called", id));
        repository.deleteById(id);
    }
}

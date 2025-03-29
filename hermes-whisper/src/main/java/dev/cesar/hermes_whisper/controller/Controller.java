package dev.cesar.hermes_whisper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.cesar.hermes_whisper.model.xai.Message;
import dev.cesar.hermes_whisper.model.xai.XaiRequest;
import dev.cesar.hermes_whisper.view.XaiClient;

@RestController
@RequestMapping("/api")
public class Controller {

    private final XaiClient client;

    public Controller(XaiClient client) {
        this.client = client;
    }

    @GetMapping("/")
    ResponseEntity<Message> promptXai(@RequestBody Message message) {
        return ResponseEntity.ok(client.prompt(XaiRequest.withDefault(message)));
    }    
}

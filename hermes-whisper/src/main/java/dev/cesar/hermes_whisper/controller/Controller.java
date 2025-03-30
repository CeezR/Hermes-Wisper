package dev.cesar.hermes_whisper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.cesar.hermes_whisper.model.xai.Message;
import dev.cesar.hermes_whisper.model.xai.XaiRequest;
import dev.cesar.hermes_whisper.view.XaiClient;
import dev.cesar.hermes_whisper.model.AppUser;
import dev.cesar.hermes_whisper.repository.AppUserRepository;

@RestController
@RequestMapping("/api")
public class Controller {

    private final XaiClient client;
    private final AppUserRepository appUserRepository;

    public Controller(XaiClient client, AppUserRepository appUserRepository) {
        this.client = client;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/")
    ResponseEntity<Message> promptXai(@RequestBody Message message) {
        return ResponseEntity.ok(client.prompt(XaiRequest.withDefault(message)));
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser newUser) {
        AppUser savedUser = appUserRepository.save(newUser);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<AppUser>> getAllUsers() {
        Iterable<AppUser> users = appUserRepository.findAll();
        return ResponseEntity.ok(users);
    }
}

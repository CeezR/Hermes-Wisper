package dev.cesar.hermes_whisper.service;

import dev.cesar.hermes_whisper.model.AppUser;
import dev.cesar.hermes_whisper.model.Vision;
import dev.cesar.hermes_whisper.repository.AppUserRepository;
import dev.cesar.hermes_whisper.repository.VisionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final VisionRepository visionRepository;

    public AppUserService(AppUserRepository appUserRepository, VisionRepository visionRepository) {
        this.appUserRepository = appUserRepository;
        this.visionRepository = visionRepository;
    }

    public Vision addVisionToUser(Long userId, Vision vision) {
        // Find the user by ID
        Optional<AppUser> userOptional = appUserRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        // Set the user for the vision
        AppUser user = userOptional.get();
        vision.setUser(user);

        // Save the vision
        return visionRepository.save(vision);
    }
}
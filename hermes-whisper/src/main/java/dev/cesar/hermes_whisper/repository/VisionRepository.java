package dev.cesar.hermes_whisper.repository;

import dev.cesar.hermes_whisper.model.Vision;
import dev.cesar.hermes_whisper.model.AppUser;
import dev.cesar.hermes_whisper.model.Vision.VisionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisionRepository extends JpaRepository<Vision, Long> {
    // Find all visions by user
    List<Vision> findByUser(AppUser user);

    // Find all visions by user and type (POSITIVE or NEGATIVE)
    List<Vision> findByUserAndType(AppUser user, VisionType type);

    // Find all visions by dimension
    List<Vision> findByDimension(String dimension);
}

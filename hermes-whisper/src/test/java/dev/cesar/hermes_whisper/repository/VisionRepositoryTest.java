package dev.cesar.hermes_whisper.repository;

import dev.cesar.hermes_whisper.model.AppUser;
import dev.cesar.hermes_whisper.model.Vision;
import dev.cesar.hermes_whisper.model.Vision.VisionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback
public class VisionRepositoryTest {

    @Autowired
    private VisionRepository visionRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    private AppUser testUser;

    @BeforeEach
    void setUp() {
        testUser = new AppUser("testuser", "testuser@example.com");
        appUserRepository.save(testUser);

        visionRepository.save(new Vision(testUser, "Career", "Become a team leader", VisionType.POSITIVE));
        visionRepository.save(new Vision(testUser, "Health", "Lose 10 pounds", VisionType.POSITIVE));
        visionRepository.save(new Vision(testUser, "Career", "Struggle with work-life balance", VisionType.NEGATIVE));
    }

    @Test
    void testFindByDimension() {
        List<Vision> careerVisions = visionRepository.findByDimension("Career");

        // Assert
        assertThat(careerVisions).hasSize(2);
        assertThat(careerVisions).allMatch(vision -> vision.getDimension().equals("Career"));
    }

    @Test
    void testFindByUser() {
        // Act
        List<Vision> userVisions = visionRepository.findByUser(testUser);

        // Assert
        assertThat(userVisions).hasSize(3);
        assertThat(userVisions).allMatch(vision -> vision.getUser().equals(testUser));
    }

    @Test
    void testFindByUserAndType() {
        // Act
        List<Vision> positiveVisions = visionRepository.findByUserAndType(testUser, VisionType.POSITIVE);

        // Assert
        assertThat(positiveVisions).hasSize(2);
        assertThat(positiveVisions).allMatch(vision -> vision.getType() == VisionType.POSITIVE);
    }
}

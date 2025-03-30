package dev.cesar.hermes_whisper.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dev.cesar.hermes_whisper.model.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void cleanDatabase() {
        // Clean up the database before each test
        entityManager.createQuery("DELETE FROM Vision").executeUpdate();
        entityManager.createQuery("DELETE FROM AppUser").executeUpdate();
    }

    @Test
    void testFindByUsername_NotFound() {
        // Act
        Optional<AppUser> foundUser = appUserRepository.findByUsername("nonexistentuser");

        // Assert
        assertThat(foundUser).isNotPresent();
    }

    @Test
    void testCreateNewUser() {
        // Arrange
        AppUser newUser = new AppUser("testuser", "testuser@example.com");

        // Act
        AppUser savedUser = appUserRepository.save(newUser);

        // Assert
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull(); // Ensure the ID is generated
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
        assertThat(savedUser.getEmail()).isEqualTo("testuser@example.com");
    }
}
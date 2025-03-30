package dev.cesar.hermes_whisper.service;

import dev.cesar.hermes_whisper.model.AppUser;
import dev.cesar.hermes_whisper.model.Vision;
import dev.cesar.hermes_whisper.model.Vision.VisionType;
import dev.cesar.hermes_whisper.repository.AppUserRepository;
import dev.cesar.hermes_whisper.repository.VisionRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppUserServiceTest {

    private final AppUserRepository appUserRepository = mock(AppUserRepository.class);
    private final VisionRepository visionRepository = mock(VisionRepository.class);
    private final AppUserService appUserService = new AppUserService(appUserRepository, visionRepository);

    @Test
    void addVisionToUser() {
        // Arrange
        AppUser mockUser = new AppUser("testuser", "testuser@example.com");
        mockUser.setId(1L);

        Vision mockVision = new Vision(mockUser, "Career", "Become a team leader", VisionType.POSITIVE);
        mockVision.setId(1L);

        when(appUserRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(visionRepository.save(any(Vision.class))).thenReturn(mockVision);

        // Act
        Vision result = appUserService.addVisionToUser(1L, mockVision);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDimension()).isEqualTo("Career");
        assertThat(result.getDescription()).isEqualTo("Become a team leader");
        assertThat(result.getType()).isEqualTo(VisionType.POSITIVE);
        assertThat(result.getUser()).isEqualTo(mockUser);

        // Verify interactions
        verify(appUserRepository, times(1)).findById(1L);
        verify(visionRepository, times(1)).save(any(Vision.class));
    }

    @Test
    void addVisionToUser_UserNotFound() {
        // Arrange
        Vision mockVision = new Vision(null, "Career", "Become a team leader", VisionType.POSITIVE);
        mockVision.setId(1L);
        
        when(appUserRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appUserService.addVisionToUser(1L, mockVision);
        });

        assertThat(exception.getMessage()).isEqualTo("User not found with ID: 1");

        // Verify interactions
        verify(appUserRepository, times(1)).findById(1L);
        verify(visionRepository, never()).save(any(Vision.class));
    }
}

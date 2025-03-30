package dev.cesar.hermes_whisper.controller;

import dev.cesar.hermes_whisper.model.AppUser;
import dev.cesar.hermes_whisper.model.Vision;
import dev.cesar.hermes_whisper.model.Vision.VisionType;
import dev.cesar.hermes_whisper.repository.AppUserRepository;
import dev.cesar.hermes_whisper.service.AppUserService;
import dev.cesar.hermes_whisper.view.XaiClient;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

@WebMvcTest(Controller.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private AppUserRepository appUserRepository;
    @MockitoBean
    private XaiClient xaiClient;
    @MockitoBean
    private AppUserService appUserService;

    @Test
    void testCreateUser() throws Exception {
        // Arrange
        AppUser mockUser = new AppUser("testuser", "testuser@example.com");
        mockUser.setId(1L);

        when(appUserRepository.save(any(AppUser.class))).thenReturn(mockUser);

        String requestBody = """
            {
                "username": "testuser",
                "email": "testuser@example.com"
            }
        """;

        // Act & Assert
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("testuser@example.com"));

        // Verify that the repository's save method was called
        Mockito.verify(appUserRepository).save(any(AppUser.class));
    }

    @Test
    void testGetAllUsers() throws Exception {
        // Arrange
        AppUser user1 = new AppUser("user1", "user1@example.com");
        user1.setId(1L);
        AppUser user2 = new AppUser("user2", "user2@example.com");
        user2.setId(2L);

        when(appUserRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act & Assert
        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].username").value("user2"))
                .andExpect(jsonPath("$[1].email").value("user2@example.com"));

        // Verify that the repository's findAll method was called
        Mockito.verify(appUserRepository).findAll();
    }

    @Test
    void testAddVisionToUser() throws Exception {
        // Arrange
        AppUser mockUser = new AppUser("testuser", "testuser@example.com");
        mockUser.setId(1L);

        Vision mockVision = new Vision(mockUser, "Career", "Become a team leader", VisionType.POSITIVE);
        mockVision.setId(1L);

        when(appUserService.addVisionToUser(Mockito.eq(1L), any(Vision.class))).thenReturn(mockVision);

        String requestBody = """
            {
                "dimension": "Career",
                "description": "Become a team leader",
                "type": "POSITIVE"
            }
        """;

        // Act & Assert
        mockMvc.perform(post("/api/users/1/visions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.dimension").value("Career"))
                .andExpect(jsonPath("$.description").value("Become a team leader"))
                .andExpect(jsonPath("$.type").value("POSITIVE"))
                .andExpect(jsonPath("$.user.id").value(1))
                .andExpect(jsonPath("$.user.username").value("testuser"))
                .andExpect(jsonPath("$.user.email").value("testuser@example.com"));

        // Verify that the service method was called
        Mockito.verify(appUserService).addVisionToUser(Mockito.eq(1L), any(Vision.class));
    }




}

package dev.cesar.hermes_whisper.controller;

import dev.cesar.hermes_whisper.model.AppUser;
import dev.cesar.hermes_whisper.repository.AppUserRepository;
import dev.cesar.hermes_whisper.view.XaiClient;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppUserRepository appUserRepository;

    @MockitoBean
    private XaiClient xaiClient;

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
}

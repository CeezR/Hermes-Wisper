package dev.cesar.hermes_whisper.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<Vision> positiveVisions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<Vision> negativeVisions = new ArrayList<>();

    // Constructors
    public AppUser() {}

    public AppUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Vision> getPositiveVisions() { return positiveVisions; }
    public void setPositiveVisions(List<Vision> positiveVisions) { this.positiveVisions = positiveVisions; }

    public List<Vision> getNegativeVisions() { return negativeVisions; }
    public void setNegativeVisions(List<Vision> negativeVisions) { this.negativeVisions = negativeVisions; }
}

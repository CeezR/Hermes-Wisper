package dev.cesar.hermes_whisper.model;

import jakarta.persistence.*;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private ConsciousEntity consciousEntity;

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

    public ConsciousEntity getConsciousEntity() { return consciousEntity; }
    public void setConsciousEntity(ConsciousEntity consciousEntity) { this.consciousEntity = consciousEntity; }
}

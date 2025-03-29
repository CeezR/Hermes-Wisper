package dev.cesar.hermes_whisper.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ConsciousEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "consciousEntity")
    private List<Vision> positiveVisions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "consciousEntity")
    private List<Vision> negativeVisions = new ArrayList<>();

    // Constructors
    public ConsciousEntity() {}

    public ConsciousEntity(AppUser user) {
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }

    public List<Vision> getPositiveVisions() { return positiveVisions; }
    public void setPositiveVisions(List<Vision> positiveVisions) { this.positiveVisions = positiveVisions; }

    public List<Vision> getNegativeVisions() { return negativeVisions; }
    public void setNegativeVisions(List<Vision> negativeVisions) { this.negativeVisions = negativeVisions; }
}

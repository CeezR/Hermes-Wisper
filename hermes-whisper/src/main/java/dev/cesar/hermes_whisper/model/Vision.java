package dev.cesar.hermes_whisper.model;

import jakarta.persistence.*;

@Entity
public class Vision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(nullable = false)
    private String dimension; // e.g., "Physical Health", "Relationships", "Career"

    @Column(nullable = false, length = 500)
    private String description; // e.g., "Feel comfortable in my body" or "Lose contact with family"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisionType type; // POSITIVE or NEGATIVE

    // Enum for Vision Type
    public enum VisionType {
        POSITIVE, NEGATIVE
    }

    // Constructors
    public Vision() {}

    public Vision(AppUser user, String dimension, String description, VisionType type) {
        this.user = user;
        this.dimension = dimension;
        this.description = description;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }

    public String getDimension() { return dimension; }
    public void setDimension(String dimension) { this.dimension = dimension; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public VisionType getType() { return type; }
    public void setType(VisionType type) { this.type = type; }
}

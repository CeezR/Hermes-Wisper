package dev.cesar.hermes_whisper.model.xai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum Role {
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant");

    private final String type;

    Role(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    @JsonCreator
    public static Role fromType(String type) {
        for (Role role : Role.values()) {
            if (role.type.equalsIgnoreCase(type)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role: " + type);
    }


}

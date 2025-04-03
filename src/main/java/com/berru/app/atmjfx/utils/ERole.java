package com.berru.app.atmjfx.utils;

public enum ERole {
    USER("User"),
    MODERATOR("Moderator"),
    ADMIN("Administrator");

    private final String description;

    ERole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 📌 Safely converts a String to an Enum.
     */
    public static ERole fromString(String role) {
        try {
            return ERole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("❌ Invalid role: " + role);
        }
    }

    @Override
    public String toString() {
        return description; // Text displayed in the ComboBox
    }
}
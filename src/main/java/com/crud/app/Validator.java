package com.crud.app;

// Utility class with static methods (no object creation needed)
public class Validator {

    // Static method - shared logic, no instance required
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidId(int id) {
        return id > 0;
    }
}

package com.crud.app;

import com.crud.exception.UserNotFoundException;
import com.crud.model.User;
import com.crud.service.UserService;
import com.crud.service.UserServiceImpl;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        // Polymorphism: UserService reference -> UserServiceImpl object
        UserService service = new UserServiceImpl();

        System.out.println("========== USER CRUD APPLICATION ==========\n");

        // ---- CREATE ----
        System.out.println("--- CREATE ---");
        try {
            // Validate before creating (Static method usage)
            String email1 = "vikas@mail.com";
            if (!Validator.isValidEmail(email1)) {
                throw new IllegalArgumentException("Invalid email: " + email1);
            }
            service.createUser(new User(1, "Vikas", email1));
            service.createUser(new User(2, "Ananya", "ananya@mail.com"));
            service.createUser(new User(3, "Rahul", "rahul@mail.com"));
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // ---- READ ----
        System.out.println("\n--- READ ---");
        try {
            System.out.println("Fetched: " + service.getUser(1));
            System.out.println("Fetched: " + service.getUser(2));
        } catch (UserNotFoundException e) {
            System.out.println("❌ Error [" + e.getErrorCode() + "]: " + e.getMessage());
        }

        // ---- READ ALL ----
        System.out.println("\n--- READ ALL ---");
        List<User> allUsers = service.getAllUsers();
        allUsers.forEach(System.out::println);

        // ---- UPDATE (name only) ----
        System.out.println("\n--- UPDATE (name only) ---");
        try {
            service.updateUser(1, "Vikas Gowda");
        } catch (UserNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // ---- UPDATE (name + email) - Overloaded method ----
        System.out.println("\n--- UPDATE (name + email) ---");
        try {
            service.updateUser(2, "Ananya Sharma", "ananya.sharma@mail.com");
        } catch (UserNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // ---- DELETE ----
        System.out.println("\n--- DELETE ---");
        try {
            service.deleteUser(3);
        } catch (UserNotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // ---- EXCEPTION CASE: User not found ----
        System.out.println("\n--- EXCEPTION: Fetch deleted/non-existent user ---");
        try {
            System.out.println(service.getUser(3)); // ID 3 was deleted
        } catch (UserNotFoundException e) {
            System.out.println("❌ Error [" + e.getErrorCode() + "]: " + e.getMessage());
        }

        // ---- EXCEPTION CASE: Duplicate create ----
        System.out.println("\n--- EXCEPTION: Duplicate user creation ---");
        try {
            service.createUser(new User(1, "Duplicate", "dup@mail.com")); // ID 1 exists
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        // ---- FINAL STATE ----
        System.out.println("\n--- FINAL STATE ---");
        service.getAllUsers().forEach(System.out::println);

        System.out.println("\n========== END ==========");
    }
}

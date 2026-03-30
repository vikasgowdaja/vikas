package com.crud.service;

import com.crud.exception.UserNotFoundException;
import com.crud.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl extends UserService {

    // HashMap for O(1) lookup (Collections)
    private Map<Integer, User> userMap = new HashMap<>();

    // CREATE
    @Override
    public void createUser(User user) {
        if (userMap.containsKey(user.getId())) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " already exists.");
        }
        userMap.put(user.getId(), user);
        System.out.println("✅ User created: " + user);
    }

    // READ
    @Override
    public User getUser(int id) {
        if (!userMap.containsKey(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        return userMap.get(id);
    }

    // UPDATE (name only) - Overloaded
    @Override
    public void updateUser(int id, String name) {
        User user = getUser(id); // reuse method (DRY principle)
        user.setName(name);
        System.out.println("✅ User name updated: " + user);
    }

    // UPDATE (name + email) - Method Overloading
    @Override
    public void updateUser(int id, String name, String email) {
        User user = getUser(id);
        user.setName(name);
        user.setEmail(email);
        System.out.println("✅ User name & email updated: " + user);
    }

    // DELETE
    @Override
    public void deleteUser(int id) {
        if (!userMap.containsKey(id)) {
            throw new UserNotFoundException("Cannot delete. User not found with ID: " + id);
        }
        User removed = userMap.remove(id);
        System.out.println("🗑️  User deleted: " + removed);
    }

    // READ ALL (using ArrayList + HashMap values)
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }
}

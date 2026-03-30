package com.crud.service;

import com.crud.model.User;
import java.util.List;

public abstract class UserService {

    // Abstract methods (Abstraction - defining contract)
    public abstract void createUser(User user);

    public abstract User getUser(int id);

    public abstract void updateUser(int id, String name);

    public abstract void deleteUser(int id);

    // Overloaded method (Method Overloading)
    public abstract void updateUser(int id, String name, String email);

    public abstract List<User> getAllUsers();
}

package com.cbo.account.management.user.application.create;

import org.springframework.stereotype.Service;

@Service
public final class UserCreator {
    public void createUser(String userId, String name, String email, String password) {

        // Logic to create a user
        // This is just a placeholder implementation
        System.out.println("User created with ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

    }
}

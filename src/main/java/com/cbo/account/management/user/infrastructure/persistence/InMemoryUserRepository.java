package com.cbo.account.management.user.infrastructure.persistence;

import com.cbo.account.management.shared.domain.Service;
import com.cbo.account.management.user.domain.model.User;
import com.cbo.account.management.user.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Optional;

@Service
public class InMemoryUserRepository implements UserRepository {
    private final HashMap<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.put(user.getId().value(), user);
        System.out.println("User saved: " + user);
    }

    @Override
    public Optional<User> search(String userId) {
        return Optional.ofNullable(users.get(userId));
    }
}
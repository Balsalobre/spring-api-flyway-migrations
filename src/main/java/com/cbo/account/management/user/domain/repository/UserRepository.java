package com.cbo.account.management.user.domain.repository;

import com.cbo.account.management.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> search(String userId);
}

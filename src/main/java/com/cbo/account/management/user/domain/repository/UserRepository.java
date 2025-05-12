package com.cbo.account.management.user.domain.repository;

import com.cbo.account.management.user.domain.model.User;

public interface UserRepository {
    void save(User user);
}

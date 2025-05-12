package com.cbo.account.management.user.application.create;

import com.cbo.account.management.shared.domain.Service;
import com.cbo.account.management.user.domain.model.User;
import com.cbo.account.management.user.domain.repository.UserRepository;
import com.cbo.account.management.user.domain.valueobject.Address;

import java.time.LocalDate;
import java.util.UUID;


@Service
public final class CustomerCreator {

    private UserRepository repository;

    public CustomerCreator(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(String id, String firstName, String lastName, String email, String phoneNumber,
                           String username, String rawPassword, String nationalId,
                           LocalDate dateOfBirth, Address address, String createdBy) {
        UUID userId = UUID.fromString(id);
        User user = User.createNewCustomer(
                userId,
                firstName,
                lastName,
                email,
                phoneNumber,
                username,
                rawPassword,
                nationalId,
                dateOfBirth,
                address,
                createdBy
        );

        repository.save(user);
    }
}

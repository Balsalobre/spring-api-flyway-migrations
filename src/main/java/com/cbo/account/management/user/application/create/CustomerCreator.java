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

    public void createUser(CreateCustomerRequest request) {

        User user = User.createNewCustomer(
                UUID.fromString(request.getUserId()),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getUsername(),
                request.getRawPassword(),
                request.getNationalId(),
                LocalDate.parse(request.getDateOfBirth()),
                request.getAddress(),
                request.getCreatedBy()
        );

        repository.save(user);
    }
}

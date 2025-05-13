package com.cbo.account.management.user.application.create;

import com.cbo.account.management.user.domain.model.User;
import com.cbo.account.management.user.domain.repository.UserRepository;
import com.cbo.account.management.user.domain.valueobject.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
final class CustomerCreatorShould {

    @Mock
    private UserRepository repository;

    @Test
    void saveValidCustomer() throws Exception {
        CustomerCreator creator = new CustomerCreator(repository);

        UUID userId = UUID.randomUUID();
        String firstName = "John";
        String lastName = "Doe";
        String email = "jd@gmail.com";
        String phoneNumber = "1234567890";
        String username = "johndoe";
        String password = "P@ssw0rd!";
        String nationalId = "123456789";
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        Address address = new Address("123 Main St", "City", "State", "Country", "12345");
        String createdBy = "test";

        creator.createUser(
                new CreateCustomerRequest(
                        userId.toString(),
                        firstName,
                        lastName,
                        email,
                        phoneNumber,
                        username,
                        password,
                        nationalId,
                        dateOfBirth.toString(),
                        address,
                        createdBy
                )
        );

        verify(repository, times(1)).save(any(User.class));
    }
}

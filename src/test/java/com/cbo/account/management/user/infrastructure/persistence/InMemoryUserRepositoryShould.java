package com.cbo.account.management.user.infrastructure.persistence;

import com.cbo.account.management.user.domain.valueobject.Address;
import org.junit.jupiter.api.Test;
import com.cbo.account.management.user.domain.model.User;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

final class InMemoryUserRepositoryShould {
    final User user = User.createNewCustomer(
            UUID.randomUUID(),
            "John",
            "Doe",
            "dsf@fds.com",
            "1234567890",
            "johndoe",
            "P@ssw0rd!",
            "123456789",
            LocalDate.of(1990, 1, 1),
            new Address("123 Main St", "City", "State", "Country", "12345"),
            "test"
    );

    @Test
    void saveUser() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        repository.save(this.user);
    }

    @Test
    void saveNullUser() {
        InMemoryUserRepository repository = new InMemoryUserRepository();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.save(null);
        });

        String expectedMessage = "User cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void searchAnExistingUser() {
        InMemoryUserRepository repository = new InMemoryUserRepository();

        repository.save(this.user);

        User expectedUser = repository.search(user.getId().getValue()).orElse(null);

        assertEquals(user, expectedUser, "User should be the same");
    }

    @Test
    void notFoundExistingUser() {
        InMemoryUserRepository repository = new InMemoryUserRepository();

        assertFalse(repository.search(UUID.randomUUID()).isPresent());
    }
}
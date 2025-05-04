package com.cbo.account.management.user.domain.model;

import com.cbo.account.management.user.domain.model.User;
import com.cbo.account.management.user.domain.valueobject.*;

import java.time.LocalDate;
import java.time.Month;

public class UserExample {
    public static void main(String[] args) {
        // Ejemplo 1: Creación exitosa de un usuario
        try {
            User validUser = createValidUser();
            System.out.println("Usuario creado exitosamente: " + validUser);
            System.out.println("Edad del usuario: " + validUser.getAge() + " años");
            System.out.println("Dirección completa: " + validUser.getAddress());
        } catch (Exception e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        }

        // Ejemplo 2: Intento de crear un usuario menor de edad
        try {
            User underage = createUnderageUser();
            System.out.println("Usuario creado: " + underage);
        } catch (Exception e) {
            System.out.println("Error esperado (edad): " + e.getMessage());
        }

        // Ejemplo 3: Intento de crear un usuario con email inválido
        try {
            User invalidEmailUser = createUserWithInvalidEmail();
            System.out.println("Usuario creado: " + invalidEmailUser);
        } catch (Exception e) {
            System.out.println("Error esperado (email): " + e.getMessage());
        }

        // Ejemplo 4: Intento de crear un usuario con contraseña insegura
        try {
            User weakPasswordUser = createUserWithWeakPassword();
            System.out.println("Usuario creado: " + weakPasswordUser);
        } catch (Exception e) {
            System.out.println("Error esperado (contraseña): " + e.getMessage());
        }
    }

    private static User createValidUser() {
        return User.builder()
                .withNationalId("ABC12345")
                .withDateOfBirth(LocalDate.of(1990, Month.JANUARY, 15))
                .withFullName("Juan", "Carlos", "Pérez")
                .withEmail("juan.perez@example.com")
                .withPhoneNumber("+34 612345678")
                .withAddress(new Address(
                        "Calle Mayor 123",
                        "Madrid",
                        "Madrid",
                        "España",
                        "28001"
                ))
                .withUsername("jperez90")
                .withRawPassword("SecureP@ss123")
                .withRole(Role.CUSTOMER)
                .withCreatedBy("system")
                .withUpdatedBy("system")
                .build();
    }

    private static User createUnderageUser() {
        // Será rechazado por tener menos de 18 años
        return User.builder()
                .withNationalId("DEF67890")
                .withDateOfBirth(LocalDate.now().minusYears(16))
                .withFullName("Ana", "García")
                .withEmail("ana.garcia@example.com")
                .withPhoneNumber("+34 698765432")
                .withAddress(new Address(
                        "Avenida Principal 456",
                        "Barcelona",
                        "Cataluña",
                        "España",
                        "08001"
                ))
                .withUsername("agarcia")
                .withRawPassword("SecureP@ss456")
                .withCreatedBy("system")
                .withUpdatedBy("system")
                .build();
    }

    private static User createUserWithInvalidEmail() {
        // Será rechazado por formato de email inválido
        return User.builder()
                .withNationalId("GHI12345")
                .withDateOfBirth(LocalDate.of(1985, Month.MARCH, 20))
                .withFullName("Carlos", "Rodríguez")
                .withEmail("correo-invalido") // Email inválido
                .withPhoneNumber("+34 612345678")
                .withAddress(new Address(
                        "Plaza Mayor 789",
                        "Valencia",
                        "Valencia",
                        "España",
                        "46001"
                ))
                .withUsername("crodriguez")
                .withRawPassword("SecureP@ss789")
                .withCreatedBy("system")
                .withUpdatedBy("system")
                .build();
    }

    private static User createUserWithWeakPassword() {
        // Será rechazado por contraseña insegura
        return User.builder()
                .withNationalId("JKL67890")
                .withDateOfBirth(LocalDate.of(1980, Month.JULY, 10))
                .withFullName("Laura", "Martínez")
                .withEmail("laura.martinez@example.com")
                .withPhoneNumber("+34 623456789")
                .withAddress(new Address(
                        "Calle Real 321",
                        "Sevilla",
                        "Andalucía",
                        "España",
                        "41001"
                ))
                .withUsername("lmartinez")
                .withRawPassword("12345") // Contraseña débil
                .withCreatedBy("system")
                .withUpdatedBy("system")
                .build();
    }
}
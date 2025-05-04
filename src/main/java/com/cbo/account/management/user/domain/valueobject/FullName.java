package com.cbo.account.management.user.domain.valueobject;

import java.util.Objects;

public class FullName {
    private final String firstName;
    private final String lastName;
    private final String middleName;

    public FullName(String firstName, String lastName) {
        this(firstName, null, lastName);
    }

    public FullName(String firstName, String middleName, String lastName) {
        this.firstName = validateNamePart(firstName, "First name");
        this.middleName = middleName != null ? validateNamePart(middleName, "Middle name") : null;
        this.lastName = validateNamePart(lastName, "Last name");
    }

    private static String validateNamePart(String namePart, String fieldName) {
        Objects.requireNonNull(namePart, fieldName + " cannot be null");

        String trimmed = namePart.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }

        if (trimmed.length() < 2) {
            throw new IllegalArgumentException(fieldName + " must be at least 2 characters");
        }

        if (trimmed.length() > 50) {
            throw new IllegalArgumentException(fieldName + " cannot exceed 50 characters");
        }

        return trimmed;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        if (middleName != null && !middleName.isEmpty()) {
            return firstName + " " + middleName + " " + lastName;
        }
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName) &&
                Objects.equals(middleName, fullName.middleName) &&
                Objects.equals(lastName, fullName.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
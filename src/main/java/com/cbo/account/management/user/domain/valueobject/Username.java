package com.cbo.account.management.user.domain.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

public class Username {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]{3,30}$");

    private final String value;

    public Username(String username) {
        this.value = validate(username);
    }

    private static String validate(String username) {
        Objects.requireNonNull(username, "Username cannot be null");

        String trimmedUsername = username.trim();
        if (trimmedUsername.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (!USERNAME_PATTERN.matcher(trimmedUsername).matches()) {
            throw new IllegalArgumentException(
                    "Username must be 3-30 characters and can only contain letters, numbers, dots, underscores, and hyphens"
            );
        }

        return trimmedUsername;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username = (Username) o;
        return Objects.equals(value, username.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
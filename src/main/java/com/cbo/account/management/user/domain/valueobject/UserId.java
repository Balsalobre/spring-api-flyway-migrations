package com.cbo.account.management.user.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class UserId {
    private final UUID value;

    private UserId(UUID value) {
        this.value = Objects.requireNonNull(value, "User ID cannot be null");
    }

    public static UserId create() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId fromUUID(UUID uuid) {
        return new UserId(uuid);
    }

    public static UserId fromString(String uuid) {
        Objects.requireNonNull(uuid, "User ID string cannot be null");
        try {
            return new UserId(UUID.fromString(uuid));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + uuid, e);
        }
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
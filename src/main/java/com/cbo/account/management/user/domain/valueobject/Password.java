package com.cbo.account.management.user.domain.valueobject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents a password value object.
 * This class is immutable and should be used to encapsulate password-related logic.
 * It provides methods to create a password from a raw string, validate the password format,
 * and hash the password securely.
 *
 * @example password = Password.fromRawPassword("P@ssw0rd!");
 */
public class Password {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    private final String passwordHash;

    private Password(String passwordHash) {
        this.passwordHash = Objects.requireNonNull(passwordHash, "Password hash cannot be null");
    }

    public static Password fromRawPassword(String rawPassword) {
        validateRawPassword(rawPassword);
        return new Password(hashPassword(rawPassword));
    }

    public static Password fromHash(String passwordHash) {
        Objects.requireNonNull(passwordHash, "Password hash cannot be null");
        if (passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be empty");
        }
        return new Password(passwordHash);
    }

    private static void validateRawPassword(String rawPassword) {
        Objects.requireNonNull(rawPassword, "Password cannot be null");

        if (rawPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        if (rawPassword.length() > 100) {
            throw new IllegalArgumentException("Password cannot exceed 100 characters");
        }

        if (!PASSWORD_PATTERN.matcher(rawPassword).matches()) {
            throw new IllegalArgumentException(
                    "Password must contain at least one digit, one lowercase letter, " +
                            "one uppercase letter, one special character, and no whitespace"
            );
        }
    }

    private static String hashPassword(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public String getHash() {
        return passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(passwordHash, password.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passwordHash);
    }

    @Override
    public String toString() {
        return "********"; // For security, never return the actual hash in toString
    }
}

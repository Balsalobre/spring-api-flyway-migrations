package com.cbo.account.management.user.domain.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

public class PhoneNumber {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9+\\- ]{7,15}$");

    private final String value;

    public PhoneNumber(String phoneNumber) {
        this.value = validate(phoneNumber);
    }

    private static String validate(String phoneNumber) {
        Objects.requireNonNull(phoneNumber, "Phone number cannot be null");

        String trimmedPhone = phoneNumber.trim();
        if (trimmedPhone.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }

        if (!PHONE_PATTERN.matcher(trimmedPhone).matches()) {
            throw new IllegalArgumentException("Invalid phone number format: " + phoneNumber);
        }

        return trimmedPhone;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(value, that.value);
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
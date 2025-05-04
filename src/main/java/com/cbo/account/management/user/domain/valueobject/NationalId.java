package com.cbo.account.management.user.domain.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

public class NationalId {
    private static final Pattern NATIONAL_ID_PATTERN = Pattern.compile("^[0-9A-Z]{5,20}$");

    private final String value;

    public NationalId(String nationalId) {
        this.value = validate(nationalId);
    }

    private static String validate(String nationalId) {
        Objects.requireNonNull(nationalId, "National ID cannot be null");

        String trimmedId = nationalId.trim().toUpperCase();
        if (trimmedId.isEmpty()) {
            throw new IllegalArgumentException("National ID cannot be empty");
        }

        if (!NATIONAL_ID_PATTERN.matcher(trimmedId).matches()) {
            throw new IllegalArgumentException("Invalid national ID format: " + nationalId);
        }

        return trimmedId;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NationalId that = (NationalId) o;
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
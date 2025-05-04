package com.cbo.account.management.user.domain.valueobject;

import java.util.Objects;

public class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final String postalCode;

    public Address(String street, String city, String state, String country, String postalCode) {
        this.street = validateField(street, "Street", 5, 100);
        this.city = validateField(city, "City", 2, 50);
        this.state = validateField(state, "State/Province", 2, 50);
        this.country = validateCountry(country);
        this.postalCode = validatePostalCode(postalCode);
    }

    private String validateField(String field, String fieldName, int minLength, int maxLength) {
        Objects.requireNonNull(field, fieldName + " cannot be null");

        String trimmed = field.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }

        if (trimmed.length() < minLength) {
            throw new IllegalArgumentException(fieldName + " must be at least " + minLength + " characters");
        }

        if (trimmed.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " cannot exceed " + maxLength + " characters");
        }

        return trimmed;
    }

    private String validateCountry(String country) {
        String trimmedCountry = validateField(country, "Country", 2, 50);

        // Here you could add more specific validation for countries, e.g., check against ISO country codes
        // For this example, we're keeping it simple

        return trimmedCountry;
    }

    private String validatePostalCode(String postalCode) {
        Objects.requireNonNull(postalCode, "Postal code cannot be null");

        String trimmed = postalCode.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Postal code cannot be empty");
        }

        // Basic postal code validation
        if (trimmed.length() < 3 || trimmed.length() > 10) {
            throw new IllegalArgumentException("Postal code must be between 3 and 10 characters");
        }

        // Could add more complex postal code validation based on country

        return trimmed;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getFormattedAddress() {
        return street + ", " + city + ", " + state + ", " + country + " " + postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(country, address.country) &&
                Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, country, postalCode);
    }

    @Override
    public String toString() {
        return getFormattedAddress();
    }
}
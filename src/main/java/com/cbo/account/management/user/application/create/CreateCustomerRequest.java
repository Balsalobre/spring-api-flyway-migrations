package com.cbo.account.management.user.application.create;

import com.cbo.account.management.user.domain.valueobject.Address;

import java.util.Objects;

public final class CreateCustomerRequest {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String rawPassword;
    private String nationalId;
    private String dateOfBirth;
    private Address address;
    private String createdBy;

    public CreateCustomerRequest(String userId, String firstName, String lastName, String email, String phoneNumber, String username,
                                 String rawPassword, String nationalId, String dateOfBirth, Address address,
                                 String createdBy) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.rawPassword = rawPassword;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.createdBy = createdBy;
    }

    public String getUserId() { return userId; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateCustomerRequest)) return false;

        CreateCustomerRequest customer = (CreateCustomerRequest) o;
        return Objects.equals(userId, customer.userId) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(username, customer.username) &&
                Objects.equals(rawPassword, customer.rawPassword) &&
                Objects.equals(nationalId, customer.nationalId) &&
                Objects.equals(dateOfBirth, customer.dateOfBirth) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(createdBy, customer.createdBy);
    }
}

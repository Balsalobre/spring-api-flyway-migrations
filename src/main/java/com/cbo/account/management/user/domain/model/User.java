package com.cbo.account.management.user.domain.model;

import com.cbo.account.management.user.domain.valueobject.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private final UserId id;
    private final NationalId nationalId;
    private final LocalDate dateOfBirth;
    private final FullName fullName;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Address address;
    private final Username username;
    private final Password password;
    private final Set<Role> roles;
    private final KycStatus kycStatus;
    private final RiskRating riskRating;
    private final AccountStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String createdBy;
    private final String updatedBy;

    private User(Builder builder) {
        this.id = Objects.requireNonNull(builder.id, "User ID is required");
        this.nationalId = Objects.requireNonNull(builder.nationalId, "National ID is required");
        this.dateOfBirth = Objects.requireNonNull(builder.dateOfBirth, "Date of birth is required");
        this.fullName = Objects.requireNonNull(builder.fullName, "Full name is required");
        this.email = Objects.requireNonNull(builder.email, "Email is required");
        this.phoneNumber = Objects.requireNonNull(builder.phoneNumber, "Phone number is required");
        this.address = Objects.requireNonNull(builder.address, "Address is required");
        this.username = Objects.requireNonNull(builder.username, "Username is required");
        this.password = Objects.requireNonNull(builder.password, "Password is required");
        this.roles = Collections.unmodifiableSet(new HashSet<>(
                Objects.requireNonNull(builder.roles, "Roles are required")));
        this.kycStatus = Objects.requireNonNull(builder.kycStatus, "KYC status is required");
        this.riskRating = Objects.requireNonNull(builder.riskRating, "Risk rating is required");
        this.status = Objects.requireNonNull(builder.status, "Account status is required");
        this.createdAt = Objects.requireNonNull(builder.createdAt, "Created at is required");
        this.updatedAt = Objects.requireNonNull(builder.updatedAt, "Updated at is required");
        this.createdBy = Objects.requireNonNull(builder.createdBy, "Created by is required");
        this.updatedBy = Objects.requireNonNull(builder.updatedBy, "Updated by is required");

        validateAge();
    }

    private void validateAge() {
        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth, today);

        if (age.getYears() < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
    }

    public UserId getId() {
        return id;
    }

    public NationalId getNationalId() {
        return nationalId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public KycStatus getKycStatus() {
        return kycStatus;
    }

    public RiskRating getRiskRating() {
        return riskRating;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        return Period.between(dateOfBirth, today).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", fullName=" + fullName +
                ", email=" + email +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserId id;
        private NationalId nationalId;
        private LocalDate dateOfBirth;
        private FullName fullName;
        private Email email;
        private PhoneNumber phoneNumber;
        private Address address;
        private Username username;
        private Password password;
        private Set<Role> roles = new HashSet<>();
        private KycStatus kycStatus;
        private RiskRating riskRating;
        private AccountStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String createdBy;
        private String updatedBy;

        public Builder() {
            // Default values
            this.id = UserId.create();
            this.kycStatus = KycStatus.PENDING;
            this.riskRating = RiskRating.LOW;
            this.status = AccountStatus.PRE_ACTIVE;

            LocalDateTime now = LocalDateTime.now();
            this.createdAt = now;
            this.updatedAt = now;

            // Default role
            this.roles.add(Role.CUSTOMER);
        }

        public Builder withId(UserId id) {
            this.id = id;
            return this;
        }

        public Builder withNationalId(NationalId nationalId) {
            this.nationalId = nationalId;
            return this;
        }

        public Builder withNationalId(String nationalId) {
            this.nationalId = new NationalId(nationalId);
            return this;
        }

        public Builder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withFullName(FullName fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder withFullName(String firstName, String lastName) {
            this.fullName = new FullName(firstName, lastName);
            return this;
        }

        public Builder withFullName(String firstName, String middleName, String lastName) {
            this.fullName = new FullName(firstName, middleName, lastName);
            return this;
        }

        public Builder withEmail(Email email) {
            this.email = email;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = new Email(email);
            return this;
        }

        public Builder withPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = new PhoneNumber(phoneNumber);
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withUsername(Username username) {
            this.username = username;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = new Username(username);
            return this;
        }

        public Builder withPassword(Password password) {
            this.password = password;
            return this;
        }

        public Builder withRawPassword(String rawPassword) {
            this.password = Password.fromRawPassword(rawPassword);
            return this;
        }

        public Builder withPasswordHash(String passwordHash) {
            this.password = Password.fromHash(passwordHash);
            return this;
        }

        public Builder withRole(Role role) {
            this.roles.add(Objects.requireNonNull(role, "Role cannot be null"));
            return this;
        }

        public Builder withRoles(Set<Role> roles) {
            this.roles = new HashSet<>(Objects.requireNonNull(roles, "Roles cannot be null"));
            return this;
        }

        public Builder withKycStatus(KycStatus kycStatus) {
            this.kycStatus = kycStatus;
            return this;
        }

        public Builder withRiskRating(RiskRating riskRating) {
            this.riskRating = riskRating;
            return this;
        }

        public Builder withStatus(AccountStatus status) {
            this.status = status;
            return this;
        }

        public Builder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder withCreatedBy(String createdBy) {
            this.createdBy = Objects.requireNonNull(createdBy, "Created by cannot be null").trim();
            return this;
        }

        public Builder withUpdatedBy(String updatedBy) {
            this.updatedBy = Objects.requireNonNull(updatedBy, "Updated by cannot be null").trim();
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
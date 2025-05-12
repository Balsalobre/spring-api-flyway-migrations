package com.cbo.account.management.user.domain.model;

import com.cbo.account.management.user.domain.valueobject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class User {
    @EqualsAndHashCode.Include
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

    private static Builder commonBuilder(UUID userId, String firstName, String lastName, String email, String phoneNumber,
                                         String username, String rawPassword, String nationalId,
                                         LocalDate dateOfBirth, Address address, String createdBy) {
        return builder()
                .withId(UserId.fromUUID(userId))
                .withFullName(firstName, lastName)
                .withEmail(email)
                .withPhoneNumber(phoneNumber)
                .withUsername(username)
                .withRawPassword(rawPassword)
                .withNationalId(nationalId)
                .withDateOfBirth(dateOfBirth)
                .withAddress(address)
                .withCreatedBy(createdBy)
                .withUpdatedBy(createdBy);
    }

    public static User createNewCustomer(UUID userId, String firstName, String lastName, String email, String phoneNumber,
                                         String username, String rawPassword, String nationalId,
                                         LocalDate dateOfBirth, Address address, String createdBy) {
        return commonBuilder(userId, firstName, lastName, email, phoneNumber, username, rawPassword,
                nationalId, dateOfBirth, address, createdBy).build();
    }

    public static User createNewAdmin(UUID userId, String firstName, String lastName, String email, String phoneNumber,
                                      String username, String rawPassword, String nationalId,
                                      LocalDate dateOfBirth, Address address, String createdBy) {
        return commonBuilder(userId, firstName, lastName, email, phoneNumber, username, rawPassword,
                nationalId, dateOfBirth, address, createdBy)
                .withRole(Role.ADMIN)
                .withStatus(AccountStatus.ACTIVE)
                .withKycStatus(KycStatus.APPROVED)
                .build();
    }

    public static User reconstruct(UserId id, NationalId nationalId, LocalDate dateOfBirth, FullName fullName,
                                   Email email, PhoneNumber phoneNumber, Address address, Username username,
                                   String passwordHash, Set<Role> roles, KycStatus kycStatus, RiskRating riskRating,
                                   AccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt,
                                   String createdBy, String updatedBy) {
        return builder()
                .withId(id)
                .withNationalId(nationalId)
                .withDateOfBirth(dateOfBirth)
                .withFullName(fullName)
                .withEmail(email)
                .withPhoneNumber(phoneNumber)
                .withAddress(address)
                .withUsername(username)
                .withPasswordHash(passwordHash)
                .withRoles(roles)
                .withKycStatus(kycStatus)
                .withRiskRating(riskRating)
                .withStatus(status)
                .withCreatedAt(createdAt)
                .withUpdatedAt(updatedAt)
                .withCreatedBy(createdBy)
                .withUpdatedBy(updatedBy)
                .build();
    }

    private void validateAge() {
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
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
        private Set<Role> roles = new HashSet<>(Collections.singleton(Role.CUSTOMER));
        private KycStatus kycStatus = KycStatus.PENDING;
        private RiskRating riskRating = RiskRating.LOW;
        private AccountStatus status = AccountStatus.PRE_ACTIVE;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
        private String createdBy;
        private String updatedBy;

        public Builder withId(UserId id) {
            this.id = id;
            return this;
        }

        public Builder withId(UUID id) {
            return withId(UserId.fromUUID(id));
        }

        public Builder withNationalId(NationalId nationalId) {
            this.nationalId = nationalId;
            return this;
        }

        public Builder withNationalId(String nationalId) {
            return withNationalId(new NationalId(nationalId));
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
            return withFullName(new FullName(firstName, lastName));
        }

        public Builder withFullName(String firstName, String middleName, String lastName) {
            return withFullName(new FullName(firstName, middleName, lastName));
        }

        public Builder withEmail(Email email) {
            this.email = email;
            return this;
        }

        public Builder withEmail(String email) {
            return withEmail(new Email(email));
        }

        public Builder withPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            return withPhoneNumber(new PhoneNumber(phoneNumber));
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
            return withUsername(new Username(username));
        }

        public Builder withPassword(Password password) {
            this.password = password;
            return this;
        }

        public Builder withRawPassword(String rawPassword) {
            return withPassword(Password.fromRawPassword(rawPassword));
        }

        public Builder withPasswordHash(String passwordHash) {
            return withPassword(Password.fromHash(passwordHash));
        }

        public Builder withRole(Role role) {
            this.roles.add(Objects.requireNonNull(role));
            return this;
        }

        public Builder withRoles(Set<Role> roles) {
            this.roles = new HashSet<>(Objects.requireNonNull(roles));
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
            this.createdBy = createdBy.trim();
            return this;
        }

        public Builder withUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy.trim();
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
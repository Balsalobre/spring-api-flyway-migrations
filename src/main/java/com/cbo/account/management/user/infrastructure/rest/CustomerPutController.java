package com.cbo.account.management.user.infrastructure.rest;

import com.cbo.account.management.user.application.create.CreateCustomerRequest;
import com.cbo.account.management.user.application.create.CustomerCreator;
import com.cbo.account.management.user.domain.valueobject.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public final class CustomerPutController {
    @Autowired
    private CustomerCreator creator;

    @PutMapping("/{userId}")
    public ResponseEntity<String> create(@PathVariable String userId, @RequestBody Request request) {
        creator.createUser(new CreateCustomerRequest(
                        userId,
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPhoneNumber(),
                        request.getUsername(),
                        request.getRawPassword(),
                        request.getNationalId(),
                        request.getDateOfBirth().toString(),
                        request.getAddress(),
                        request.getCreatedBy()
                )
        );
        return new ResponseEntity(HttpStatus.CREATED);
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
final class Request {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String rawPassword;
    private String nationalId;
    private LocalDate dateOfBirth;
    private Address address;
    private String createdBy;
}
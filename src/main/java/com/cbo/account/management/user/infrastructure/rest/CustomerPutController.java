package com.cbo.account.management.user.infrastructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public final class CustomerPutController {
    //TODO: API V1
    // TODO: IMPLEMENT PUT ENDPOINT

    @PutMapping("/{userId}")
    public ResponseEntity create(@PathVariable String userId, @RequestBody Request request) {
        return new ResponseEntity(HttpStatus.CREATED);
    }


}

final class Request {
    private String name;
    private String email;
    private String password;

    public Request(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
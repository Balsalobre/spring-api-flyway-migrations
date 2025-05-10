package com.cbo.account.management.user.infrastructure.rest;

import com.cbo.account.management.user.application.create.UserCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public final class CustomerPutController {
    @Autowired
    private UserCreator creator;

    @PutMapping("/{userId}")
    public ResponseEntity create(@PathVariable String userId, @RequestBody Request request) {
        creator.createUser(userId, request.getName(), request.getEmail(), request.getPassword());
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
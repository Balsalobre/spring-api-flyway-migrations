package com.cbo.account.management.user.infrastructure.rest;

import com.cbo.utils.ApplicationTestCase;
import org.junit.jupiter.api.Test;

final class CustomerPutControllerShould extends ApplicationTestCase {
    @Test
    void createValidUser() throws Exception {
        assertRequestWithBody(
                "PUT",
                "/users/feab037c-98f2-4e33-a65b-0a4cd7010d71",
                "{\"name\": \"John Doe\", \"email\": \"jonhDoe@gmail.comº\", \"password\": \"12345678\"}",
                201
        );
    }
}
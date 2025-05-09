package com.cbo.account.management.user.infrastructure.rest;

import com.cbo.utils.ApplicationTestCase;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
final class CustomerPutControllerShould extends ApplicationTestCase {
    @Test
    void createValidUser() throws Exception {
        String requestBody = """
                {
                    "name": "John Doe",
                    "email": "jonhDoe@gmail.comº",
                    "password": "12345678"
                }
                """;

        assertRequestWithBody(
                "PUT",
                "/users/feab037c-98f2-4e33-a65b-0a4cd7010d71",
                requestBody,
                201
        );
    }
}

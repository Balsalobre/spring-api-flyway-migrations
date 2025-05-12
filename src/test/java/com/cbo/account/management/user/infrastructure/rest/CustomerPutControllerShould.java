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
                   "id": "cb9cf160-5f2b-4d05-baf6-c7833662b309",
                   "firstName": "Carlos",
                   "lastName": "Balsalobre",
                   "email": "new-client02@gmail.com",
                   "phoneNumber": "+34 666 666 666",
                   "username": "carlitos",
                   "rawPassword": "P@ssw0rd!",
                   "nationalId": "12345678A",
                   "dateOfBirth": "1990-01-01",
                   "address": {
                     "street": "Calle Condes de Barcelona",
                     "city": "Madrid",
                     "state": "Madrid",
                     "country": "Spain",
                     "postalCode": "28015"
                   },
                   "createdBy": "test"
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

package com.cbo.account.management.shared.infrastructure.rest;

import com.cbo.utils.ApplicationTestCase;
import org.junit.jupiter.api.Test;

final class HealthCheckGetControllerTest extends ApplicationTestCase {
    @Test
    void healthCheckReturnsOk() throws Exception {
        assertResponse("/health-check", 200, "{\"status\":\"OK\"}");
    }
}
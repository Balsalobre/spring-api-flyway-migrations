/**
 * Custom service annotation for domain classes.
 *
 * This annotation marks classes as domain services in our hexagonal architecture,
 * keeping the domain layer independent of Spring Framework.
 *
 * It works similarly to Spring's @Service annotation but allows us to maintain
 * a clean domain that is framework-agnostic. Classes annotated with this will be
 * automatically registered as Spring beans when used with the appropriate
 * ComponentScan configuration in the application's entry point.
 *
 * Example usage:
 *
 * @Service
 * public class UserDomainService {
 *     // Domain service implementation
 * }
 *
 * @see com.cbo.UserAccountServiceApplication for the ComponentScan configuration
 */
package com.cbo.account.management.shared.domain;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Service {
}
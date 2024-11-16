package org.ecommerce.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer's firstname is required")
        String firstName,

        @NotNull(message = "Customer's lastname is required")
        String lastName,

        @NotNull(message = "Customer's email is required")
        @Email(message = "Customer's email is not a valid email address")
        String email,
        Address address
) {
}

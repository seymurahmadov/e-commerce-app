package org.ecommerce.customer.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.ecommerce.customer.customer.entity.Address;


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

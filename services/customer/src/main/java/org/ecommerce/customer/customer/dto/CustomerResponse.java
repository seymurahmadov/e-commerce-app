package org.ecommerce.customer.customer.dto;


import org.ecommerce.customer.customer.entity.Address;

public record CustomerResponse(
        String id,
        String firstName,

        String lastName,

        String email,
        Address address
) {
}

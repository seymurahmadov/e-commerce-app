package org.ecommerce.customer.customer.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private Address address;

}

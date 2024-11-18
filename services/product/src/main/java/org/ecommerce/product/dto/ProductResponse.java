package org.ecommerce.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.query.sqm.produce.function.StandardFunctionReturnTypeResolvers;

import java.math.BigDecimal;

public record ProductResponse (
        Integer id,

        String name,

        String description,

        Double availableQuantity,

        BigDecimal price,

        Integer categoryId,

        String categoryName,

        String categoryDescription
){
}

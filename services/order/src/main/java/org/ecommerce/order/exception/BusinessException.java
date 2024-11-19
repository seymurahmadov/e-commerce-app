package org.ecommerce.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class BusinessException extends Exception {
    public BusinessException(String s) {
        super(s);
    }
}

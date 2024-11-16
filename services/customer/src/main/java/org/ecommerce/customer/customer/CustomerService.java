package org.ecommerce.customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer( CustomerRequest customerRequest) {

        var customer =  customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();

    }

//    public void updateCustomer( CustomerRequest customerRequest) throws CustomerNotFoundException {
//        customerRepository.findById(customerRequest.id()).orElseThrow(()-> new CustomerNotFoundException(
//                format("Cannot update customer:: No customer found with the provided ID::%s", customerRequest.id())
//        ));
//
//    }
}

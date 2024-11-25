package org.ecommerce.customer.customer.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.ecommerce.customer.customer.dto.CustomerRequest;
import org.ecommerce.customer.customer.dto.CustomerResponse;
import org.ecommerce.customer.customer.entity.Customer;
import org.ecommerce.customer.customer.exception.CustomerNotFoundException;
import org.ecommerce.customer.customer.mapper.CustomerMapper;
import org.ecommerce.customer.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {

        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();

    }

    public void updateCustomer(CustomerRequest customerRequest) throws CustomerNotFoundException {
        var customer = customerRepository.findById(customerRequest.id()).orElseThrow(() -> new CustomerNotFoundException(
                format("Cannot update customer:: No customer found with the provided ID::%s", customerRequest.id())
        ));

        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);

    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {

        if (StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }

        if (StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setLastName(customerRequest.lastName());
        }

        if (StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }

        if (customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomer() {
        return customerRepository.findAll().stream()
                .map(customerMapper::fromCustomer)
                .toList();

    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format("no customer found the provided ID :: %s", customerId)));
    }

    public void delete(String customerId) {
        customerRepository.deleteById(customerId);
    }
}

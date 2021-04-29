package ch.boblitz.challenge.service;

import ch.boblitz.challenge.model.CustomerEntity;
import ch.boblitz.challenge.repository.CustomerRepository;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity get(int id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public CustomerEntity save(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

}

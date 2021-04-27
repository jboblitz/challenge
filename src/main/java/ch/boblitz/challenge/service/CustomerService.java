package ch.boblitz.challenge.service;

import ch.boblitz.challenge.model.CustomerEntity;
import ch.boblitz.challenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public CustomerEntity get(int id) {
        return repository.findById(id).orElseThrow();
    }

    public CustomerEntity save(CustomerEntity customer) {
        return repository.save(customer);
    }
}

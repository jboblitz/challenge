package ch.boblitz.challenge.controller;

import ch.boblitz.challenge.model.CustomerEntity;
import ch.boblitz.challenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping(value = "/{id}")
    public CustomerEntity getCustomer(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping
    public CustomerEntity newEmployee(@RequestBody CustomerEntity customer) {
        return service.save(customer);
    }


}

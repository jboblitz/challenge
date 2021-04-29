package ch.boblitz.challenge.configuration;

import ch.boblitz.challenge.model.AddressEntity;
import ch.boblitz.challenge.model.CustomerEntity;
import ch.boblitz.challenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;


@Configuration
public class ChallengeConfiguration {

    @Autowired
    CustomerRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void initDataBase() {

        CustomerEntity e = new CustomerEntity();
        e.setFirstName("John");
        e.setLastName("Boblitz");
        e.setBirthDate(LocalDate.of(1963, 8, 7));

        AddressEntity a = new AddressEntity();
        a.setCity("Gränichen");
        a.setCountry("Switzerland");
        a.setStreet("Oberfeldstrasse 26");
        a.setPostalCode("5722");
        e.addAddress(a);

        repository.save(e);

    }

}

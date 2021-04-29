package ch.boblitz.challenge.repository;

import ch.boblitz.challenge.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
}

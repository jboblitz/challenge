package ch.boblitz.challenge;

import ch.boblitz.challenge.model.AddressEntity;
import ch.boblitz.challenge.model.CustomerEntity;
import ch.boblitz.challenge.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class NeverTrustAFrontendTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerService customerService;

    private List<CustomerEntity> customers = new ArrayList<>();

    
    @BeforeEach
    public void beforeAll() {
//        for debugging: (bp must not suspend all threads)
//        System.setProperty("java.awt.headless", "false");
//        DatabaseManagerSwing.main(new String[]{
//                "--url", "jdbc:hsqldb:mem:testdb", "--noexit"
//        });
    }

//    @Test
    void neverTrustAFrontend() throws Exception {
        createTestData();

        CustomerEntity customer2 = getCustomer(customers.get(1).getId());

        CustomerEntity customer1 = getCustomer(customers.get(0).getId());
        customer1.getAddressEntities().iterator().next().setId(customer2.getAddressEntities().iterator().next().getId());
        customer1.getAddressEntities().iterator().next().setStreet("modifying another address");

        mvc.perform(post("/customer")
                .content(objectMapper.writeValueAsString(customer1))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        assertEquals(0, getCustomer(customers.get(1).getId()).getAddressEntities().size()); // wrong! -> should be 1
    }

    private CustomerEntity getCustomer(int id) throws Exception {
        MvcResult mvcResult = mvc.perform(get("/customer/" + id)).andReturn();

        return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CustomerEntity.class);
    }

    private void createTestData() {
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setFirstName("firstname1");
        customer1.setLastName("lastname1");
        customer1.setBirthDate(LocalDate.of(1980, 8, 7));

        AddressEntity address1 = new AddressEntity();
        address1.setCity("city1");
        address1.setCountry("Switzerland");
        address1.setStreet("street1");
        address1.setPostalCode("zip1");
        customer1.addAddress(address1);

        customers.add(customerService.save(customer1));

        CustomerEntity customer2 = new CustomerEntity();
        customer2.setFirstName("firstname2");
        customer2.setLastName("lastname2");
        customer2.setBirthDate(LocalDate.of(1980, 8, 7));

        AddressEntity address2 = new AddressEntity();
        address2.setCity("city1");
        address2.setCountry("Switzerland");
        address2.setStreet("street1");
        address2.setPostalCode("zip1");
        customer2.addAddress(address2);

        customers.add(customerService.save(customer2));
    }

}

package ch.boblitz.challenge;

import ch.boblitz.challenge.controller.CustomerController;
import ch.boblitz.challenge.model.AddressEntity;
import ch.boblitz.challenge.model.CustomerEntity;
import ch.boblitz.challenge.repository.CustomerRepository;
import ch.boblitz.challenge.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootBasicCodingChallengeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerController controller;

    @Autowired
    CustomerRepository repository;

    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(get("/customer/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("John")));
    }

    @Test
    public void testPut() throws Exception {


        CustomerEntity e = new CustomerEntity();
        e.setFirstName("Birgit");
        e.setLastName("Kohl");
        e.setBirthDate(LocalDate.of(2000, 2, 21));

        AddressEntity a = new AddressEntity(e);
        a.setPostalCode("64625");
        a.setStreet("Bismarkstrasse 11");
        a.setCountry("Germany");
        a.setCity("Bensheim");

        e.addAddress(a);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String payload = objectMapper.writeValueAsString(e);


        this.mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(print())
                .andExpect(status().isOk());

//        for debugging:
//        repository.findAll().forEach(System.out::println);
    }

}

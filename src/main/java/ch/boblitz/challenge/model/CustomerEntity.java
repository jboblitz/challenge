package ch.boblitz.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId", fetch = EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<AddressEntity> addressEntities = new HashSet<>();


    public void addAddress(AddressEntity address) {
        addressEntities.add(address);
        address.setCustomerId(this);
    }

    // TODO: dead code?
    public void removeAddress(AddressEntity address) {
        addressEntities.remove(address);
        address.setCustomerId(null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First Name: ")
                .append(firstName)
                .append(" Last Name: ")
                .append(lastName)
                .append(" Birth Date : ")
                .append(birthDate);
        addressEntities.forEach(sb::append);
        return sb.toString();
    }

}

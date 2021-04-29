package ch.boblitz.challenge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@Getter
@Setter
@NoArgsConstructor
public class AddressEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private CustomerEntity customerId;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" Street: ")
                .append(street)
                .append(" Country: ")
                .append(country)
                .append(" PostalCode : ")
                .append(postalCode)
                .append(" City : ")
                .append(city);
        return sb.toString();
    }

}

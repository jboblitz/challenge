package ch.boblitz.challenge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "Address")
@Getter
@Setter
public class AddressEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @Setter(AccessLevel.NONE)
    private CustomerEntity customerId;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;


    protected AddressEntity() { }

    public AddressEntity(CustomerEntity customerId) {
        this.customerId = customerId;
    }

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

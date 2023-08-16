package med.star.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Embeddable
public class Address {
    private String street;
    private String crossings;
    private int num;
    private String city;
    private String country;

    public Address(AddressData address) {
        this.city = address.city();
        this.country = address.country();
        this.num = address.num();
        this.street = address.street();
        this.crossings = address.crossings();
    }


}

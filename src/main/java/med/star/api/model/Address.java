package med.star.api.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.star.api.records.doctor.request.AddressData;

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

package med.star.api.domain.address;

import jakarta.validation.constraints.NotBlank;
public record AddressData(
        @NotBlank
        String street,
        @NotBlank
        String crossings,
        @NotBlank
        int num,
        @NotBlank
        String city,
        @NotBlank
        String country) {
}

package med.star.api.records.doctor.request;

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

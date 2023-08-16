package med.star.api.domain.patient.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.star.api.domain.address.AddressData;

public record PatientData(
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotNull
        int age,
        @NotNull
        GenderData gender,
        @NotBlank
        @Email
        String email,
        @NotNull
        AddressData address
        ) {
}

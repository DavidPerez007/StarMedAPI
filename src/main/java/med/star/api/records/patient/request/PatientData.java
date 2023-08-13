package med.star.api.records.patient.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.star.api.records.doctor.request.AddressData;
import med.star.api.records.patient.request.GenderData;

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

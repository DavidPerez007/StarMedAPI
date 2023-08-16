package med.star.api.domain.doctor.request;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.*;
import med.star.api.domain.address.AddressData;

public record DoctorData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //Numeric values between 4 and 6 digits long
        String certification,
        @NotBlank
        String phoneNumber,
        @NotNull
        @Embedded
        MedSpecialty specialty,
        @NotNull
        @Embedded
        AddressData address

        ) {
}

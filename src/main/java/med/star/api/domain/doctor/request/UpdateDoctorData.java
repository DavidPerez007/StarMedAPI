package med.star.api.domain.doctor.request;

import jakarta.validation.constraints.NotNull;
import med.star.api.domain.address.AddressData;

public record UpdateDoctorData(@NotNull Long id, String email, MedSpecialty medSpecialty, String phoneNumber, AddressData address) {}

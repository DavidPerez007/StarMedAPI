package med.star.api.records.doctor.request;

import jakarta.validation.constraints.NotNull;
import med.star.api.model.Address;

public record UpdateDoctorData(@NotNull Long id, String email, MedSpecialty medSpecialty, String phoneNumber, AddressData address) {}

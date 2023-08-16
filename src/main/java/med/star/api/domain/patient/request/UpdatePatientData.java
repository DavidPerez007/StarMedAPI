package med.star.api.domain.patient.request;

import jakarta.validation.constraints.NotNull;
import med.star.api.domain.address.Address;
import med.star.api.domain.patient.Patient;

public record UpdatePatientData(@NotNull Long id, Integer age, GenderData gender, String email, Address address) {
    public UpdatePatientData(Patient patient){
        this(patient.getId(), patient.getAge(), patient.getGender(), patient.getEmail(), patient.getAddress());
    }
}

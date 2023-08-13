package med.star.api.records.patient.request;

import jakarta.validation.constraints.NotNull;
import med.star.api.model.Address;
import med.star.api.model.Patient;
import med.star.api.records.patient.request.GenderData;

public record UpdatePatientData(@NotNull Long id, Integer age, GenderData gender, String email, Address address) {
    public UpdatePatientData(Patient patient){
        this(patient.getId(), patient.getAge(), patient.getGender(), patient.getEmail(), patient.getAddress());
    }
}

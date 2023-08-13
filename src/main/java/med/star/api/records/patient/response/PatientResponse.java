package med.star.api.records.patient.response;

import med.star.api.model.Patient;
import med.star.api.repository.PatientRepository;

public record PatientResponse(String name, String lastname, int age, String email) {
    public PatientResponse(Patient patient){
        this(patient.getName(), patient.getLastname(), patient.getAge(), patient.getEmail());
    }
}

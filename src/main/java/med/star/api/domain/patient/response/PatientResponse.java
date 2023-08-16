package med.star.api.domain.patient.response;

import med.star.api.domain.patient.Patient;

public record PatientResponse(String name, String lastname, int age, String email) {
    public PatientResponse(Patient patient){
        this(patient.getName(), patient.getLastname(), patient.getAge(), patient.getEmail());
    }
}

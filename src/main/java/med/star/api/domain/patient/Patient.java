package med.star.api.domain.patient;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.star.api.domain.address.Address;
import med.star.api.domain.patient.request.GenderData;
import med.star.api.domain.patient.request.PatientData;
import med.star.api.domain.patient.request.UpdatePatientData;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastname;
    private Integer age;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private GenderData gender;
    private String email;
    private boolean isDischarged;

    public Patient(PatientData dto){
        this.name = dto.name();
        this.lastname = dto.lastname();
        this.email = dto.email();
        this.address = new Address(dto.address());
        this.age = dto.age();
        this.gender = dto.gender();
        this.isDischarged = false;

    }

    public void updatePatient(UpdatePatientData patient) {
        if(patient.age() != null) this.age = patient.age();
        if(patient.email() != null) this.email = patient.email();
        if(patient.address() != null) this.address = patient.address();
        if(patient.gender() != null) this.gender = patient.gender();

    }

    public void deletePatient(){
        this.isDischarged = true;
    }
}

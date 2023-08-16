package med.star.api.domain.doctor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import med.star.api.domain.address.Address;
import med.star.api.domain.doctor.request.DoctorData;
import med.star.api.domain.doctor.request.MedSpecialty;
import med.star.api.domain.doctor.request.UpdateDoctorData;

@Entity(name = "Doctor")
@Data
@Table(name="doctor")
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private MedSpecialty specialty;
    private String certification;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Embedded
    private Address address;
    @Column(name = "isactive")
    private boolean isActive;

    public Doctor(DoctorData dto){
        this.name = dto.name();
        this.email = dto.email();
        this.address = new Address(dto.address());
        this.specialty = dto.specialty();
        this.phoneNumber = dto.phoneNumber();
        this.certification = dto.certification();
        this.isActive = true;
    }

    public Doctor updateDoctor(UpdateDoctorData doctor){
        if(doctor.email() != null) this.email = doctor.email();
        if(doctor.address() != null) this.address = new Address(doctor.address());
        if(doctor.medSpecialty() != null) this.specialty = doctor.medSpecialty();
        if(doctor.phoneNumber() != null) this.phoneNumber = doctor.phoneNumber();
        return this;
    }

    public void deleteDoctor(){
        this.isActive = false;
    }
}

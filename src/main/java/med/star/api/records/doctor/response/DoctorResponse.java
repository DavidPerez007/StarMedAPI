package med.star.api.records.doctor.response;

import lombok.AllArgsConstructor;
import med.star.api.model.Doctor;
import med.star.api.records.doctor.request.MedSpecialty;


public record DoctorResponse(Long id, String name, MedSpecialty medSpecialty, String email, String phoneNumber, boolean isActive){
    public DoctorResponse(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), doctor.getPhoneNumber(), doctor.isActive());
    }
}

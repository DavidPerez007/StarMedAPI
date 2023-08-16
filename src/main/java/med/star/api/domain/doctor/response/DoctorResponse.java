package med.star.api.domain.doctor.response;

import med.star.api.domain.doctor.Doctor;
import med.star.api.domain.doctor.request.MedSpecialty;


public record DoctorResponse(Long id, String name, MedSpecialty medSpecialty, String email, String phoneNumber, boolean isActive){
    public DoctorResponse(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), doctor.getPhoneNumber(), doctor.isActive());
    }
}

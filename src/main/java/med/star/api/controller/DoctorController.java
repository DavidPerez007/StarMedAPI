package med.star.api.controller;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.star.api.model.Doctor;
import med.star.api.records.doctor.request.DoctorData;
import med.star.api.records.doctor.request.UpdateDoctorData;
import med.star.api.records.doctor.response.DoctorResponse;
import med.star.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    public ResponseEntity<DoctorResponse> registerDoctor(@RequestBody @Valid DoctorData doctor, UriComponentsBuilder urlBuilder){
        Doctor newDoctor = this.doctorRepository.save(new Doctor(doctor));
        DoctorResponse response = new DoctorResponse(newDoctor.getId(), newDoctor.getName(), newDoctor.getSpecialty(), newDoctor.getEmail(), newDoctor.getPhoneNumber(), newDoctor.isActive());
        URI uri = urlBuilder.path("/doctor/{id}").buildAndExpand(newDoctor.getId()).toUri(); //We're building the URI to pass parameter in RepsponseEntity.created
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public Page<DoctorResponse> getAllDoctors(@PageableDefault(size = 2, sort = "name") Pageable page){
        Page<DoctorResponse> doctors = this.doctorRepository.findByIsActiveTrue(page).map(DoctorResponse::new);

        return doctors;
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctor(@PathVariable Long id){
        Doctor doctor = this.doctorRepository.getReferenceById(id);
        var doctorResponse = new DoctorResponse(doctor.getId(), doctor.getName(), doctor.getSpecialty(), doctor.getEmail(), doctor.getPhoneNumber(), doctor.isActive());
        return ResponseEntity.ok(doctorResponse);
    }

    @PutMapping
    @Transactional //So that when Doctor updates his data, it will save to db
    public ResponseEntity<DoctorResponse> updateDoctor(@RequestBody @Valid UpdateDoctorData updatedDoctor){
        Doctor doctorToUpdate = this.doctorRepository.getReferenceById(updatedDoctor.id());
        doctorToUpdate.updateDoctor(updatedDoctor);
        return ResponseEntity.ok(new DoctorResponse(doctorToUpdate.getId(), doctorToUpdate.getName(), doctorToUpdate.getSpecialty(), doctorToUpdate.getEmail(), doctorToUpdate.getPhoneNumber(), doctorToUpdate.isActive()));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        Doctor doctorToDelete = this.doctorRepository.getReferenceById(id);
        doctorToDelete.deleteDoctor();
        return ResponseEntity.noContent().build();
    }
}

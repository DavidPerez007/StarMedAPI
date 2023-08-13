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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    public void registerDoctor(
            @RequestBody
            @Valid
            DoctorData doctor
    ){
        this.doctorRepository.save(new Doctor(doctor));
    }

    @GetMapping
    public Page<DoctorResponse> getAllDoctors(@PageableDefault(size = 2, sort = "name") Pageable page){
        Page<DoctorResponse> doctors = this.doctorRepository.findByIsActiveTrue(page).map(DoctorResponse::new);

        return doctors;
    }

    @PutMapping
    @Transactional //So that when Doctor updates his data, it will save to db
    public void updateDoctor(@RequestBody @Valid UpdateDoctorData updatedDoctor){
        Doctor doctorToUpdate = this.doctorRepository.getReferenceById(updatedDoctor.id());
        doctorToUpdate.updateDoctor(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id){
        Doctor doctorToDelete = this.doctorRepository.getReferenceById(id);
        doctorToDelete.deleteDoctor();
    }
}

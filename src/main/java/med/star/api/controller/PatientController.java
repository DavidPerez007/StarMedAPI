package med.star.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.star.api.model.Patient;
import med.star.api.records.patient.request.PatientData;
import med.star.api.records.patient.response.PatientResponse;
import med.star.api.records.patient.request.UpdatePatientData;
import med.star.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public void registerPatient(@RequestBody @Valid PatientData patient) {
        this.patientRepository.save(new Patient(patient));
    }

    @GetMapping
    public Page<PatientResponse> getPatients(@PageableDefault(size = 2, sort = "name") Pageable page) {
        Page<PatientResponse> patients = this.patientRepository.findByIsDischargedFalse(page).map(PatientResponse::new);
        return patients;
    }

    @PutMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid UpdatePatientData updatedPatient) {
        Patient patientToUpdate = this.patientRepository.getReferenceById(updatedPatient.id());
        patientToUpdate.updatePatient(updatedPatient);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatient(@PathVariable Long id){
        Patient patientToDelete = this.patientRepository.getReferenceById(id);
        patientToDelete.deletePatient();
    }
}

package med.star.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.star.api.domain.patient.Patient;
import med.star.api.domain.patient.request.PatientData;
import med.star.api.domain.patient.response.PatientResponse;
import med.star.api.domain.patient.request.UpdatePatientData;
import med.star.api.repository.PatientRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<PatientResponse> registerPatient(@RequestBody @Valid PatientData patient, UriComponentsBuilder urlBuilder) {
        Patient newPatient = this.patientRepository.save(new Patient(patient));
        PatientResponse response = new PatientResponse(newPatient);
        URI url = urlBuilder.path("/patients/{id}").buildAndExpand(newPatient.getId()).toUri();
        return ResponseEntity.created(url).body(response);
    }

    @GetMapping
    public Page<PatientResponse> getPatients(@PageableDefault(size = 2, sort = "name") Pageable page) {
        Page<PatientResponse> patients = this.patientRepository.findByIsDischargedFalse(page).map(PatientResponse::new);
        return patients;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatient(@PathVariable Long id){
        Patient patient = this.patientRepository.getReferenceById(id);
        PatientResponse response = new PatientResponse(patient);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientResponse> updatePatient(@RequestBody @Valid UpdatePatientData updatedPatient) {
        Patient patientToUpdate = this.patientRepository.getReferenceById(updatedPatient.id());
        patientToUpdate.updatePatient(updatedPatient);
        PatientResponse response = new PatientResponse(patientToUpdate);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        Patient patientToDelete = this.patientRepository.getReferenceById(id);
        patientToDelete.deletePatient();
        return ResponseEntity.noContent().build();
    }
}

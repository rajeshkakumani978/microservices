package com.app.patient.controller;

import com.app.patient.dto.PatientRequestDTO;
import com.app.patient.dto.PatientResponseDTO;
import com.app.patient.dto.validators.CreatePatientValidationGroup;
import com.app.patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@AllArgsConstructor
@Tag(name = "Patient", description = "API for managing patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        List<PatientResponseDTO> patientResponseDTOS = patientService.getPatients();
        if(patientResponseDTOS != null) {
            return new ResponseEntity<>(patientResponseDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Builder.Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        return new ResponseEntity<>(patientService.createPatient(patientRequestDTO), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                            @Validated({Builder.Default.class})
                                                            @RequestBody PatientRequestDTO patientRequestDTO){
        return new ResponseEntity<>(patientService.updatePatient(id,patientRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable UUID id){
        patientService.deletePatientById(id);
        return new ResponseEntity<>("Patient Data Deleted successfully", HttpStatus.OK);
    }
}

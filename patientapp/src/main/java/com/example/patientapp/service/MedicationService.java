package com.example.patientapp.service;


import com.example.patientapp.model.Medication;
import com.example.patientapp.model.Patient;
import com.example.patientapp.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private PatientService patientService;

    // Add a new medication
    public void addMedication(Medication medication, Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        medication.setPatient(patient);
        medicationRepository.save(medication);
    }

    // Get all medications
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    // Delete a medication
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
}
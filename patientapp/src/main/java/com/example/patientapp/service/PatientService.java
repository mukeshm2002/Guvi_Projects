package com.example.patientapp.service;


import com.example.patientapp.model.Appointment;
import com.example.patientapp.model.Medication;
import com.example.patientapp.model.Patient;
import com.example.patientapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Save a new patient
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get a patient by ID
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));
    }

    // Update a patient
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + id));

        // Update fields
        existingPatient.setName(updatedPatient.getName());
        existingPatient.setEmail(updatedPatient.getEmail());
        existingPatient.setContactDetails(updatedPatient.getContactDetails());
        existingPatient.setMedicalHistory(updatedPatient.getMedicalHistory());

        // Save the updated patient
        return patientRepository.save(existingPatient);
    }

    // Delete a patient
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    // Add an appointment to a patient
    public Patient addAppointmentToPatient(Long patientId, Appointment appointment) {
        Patient patient = getPatientById(patientId);
        patient.addAppointment(appointment);
        return patientRepository.save(patient);
    }

    // Add a medication to a patient
    public Patient addMedicationToPatient(Long patientId, Medication medication) {
        Patient patient = getPatientById(patientId);
        patient.addMedication(medication);
        return patientRepository.save(patient);
    }
}
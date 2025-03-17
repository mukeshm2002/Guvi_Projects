package com.example.patientapp.service;



import com.example.patientapp.model.Appointment;
import com.example.patientapp.model.Patient;
import com.example.patientapp.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientService patientService;

    // Book a new appointment
    public void bookAppointment(Appointment appointment, Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Cancel an appointment
    public void cancelAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
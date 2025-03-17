package com.example.patientapp.controller;



import com.example.patientapp.model.Appointment;
import com.example.patientapp.model.Medication;
import com.example.patientapp.model.Patient;
import com.example.patientapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/register";
    }

    // Register a new patient
    @PostMapping("/register")
    public String registerPatient(@ModelAttribute Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    // Show all patients
    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients/list";
    }

    // Show patient profile
    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patients/profile";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patients/edit";
    }

    // Update patient details
    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:/patients/profile/" + id;
    }

    // Delete a patient
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    // Show form to add an appointment
    @GetMapping("/{id}/appointments/add")
    public String showAddAppointmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("patientId", id);
        model.addAttribute("appointment", new Appointment());
        return "appointment/book";
    }

    // Add an appointment to a patient
    @PostMapping("/{id}/appointments/add")
    public String addAppointmentToPatient(@PathVariable Long id, @ModelAttribute Appointment appointment) {
        patientService.addAppointmentToPatient(id, appointment);
        return "redirect:/patients/profile/" + id;
    }

    // Show form to add a medication
    @GetMapping("/{id}/medications/add")
    public String showAddMedicationForm(@PathVariable Long id, Model model) {
        model.addAttribute("patientId", id);
        model.addAttribute("medication", new Medication());
        return "medication/add";
    }

    // Add a medication to a patient
    @PostMapping("/{id}/medications/add")
    public String addMedicationToPatient(@PathVariable Long id, @ModelAttribute Medication medication) {
        patientService.addMedicationToPatient(id, medication);
        return "redirect:/patients/profile/" + id;
    }
}
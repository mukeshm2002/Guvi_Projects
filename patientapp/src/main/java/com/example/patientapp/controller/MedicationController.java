package com.example.patientapp.controller;


import com.example.patientapp.model.Medication;
import com.example.patientapp.service.MedicationService;
import com.example.patientapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private PatientService patientService;

    // Show form to add a new medication
    @GetMapping("/add")
    public String showAddMedicationForm(Model model) {
        model.addAttribute("medication", new Medication());
        model.addAttribute("patients", patientService.getAllPatients());
        return "medication/add";
    }

    // Add a new medication
    @PostMapping("/add")
    public String addMedication(@ModelAttribute Medication medication, @RequestParam Long patientId) {
        medicationService.addMedication(medication, patientId);
        return "redirect:/medications/list";
    }

    // View all medications
    @GetMapping("/list")
    public String listMedications(Model model) {
        model.addAttribute("medications", medicationService.getAllMedications());
        return "medication/list";
    }

    // Delete a medication
    @GetMapping("/delete/{id}")
    public String deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return "redirect:/medications/list";
    }
}
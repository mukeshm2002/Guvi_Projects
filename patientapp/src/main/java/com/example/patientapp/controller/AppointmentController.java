package com.example.patientapp.controller;



import com.example.patientapp.model.Appointment;
import com.example.patientapp.service.AppointmentService;
import com.example.patientapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    // Show form to book a new appointment
    @GetMapping("/book")
    public String showBookAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAllPatients());
        return "appointment/book";
    }

    // Book a new appointment
    @PostMapping("/book")
    public String bookAppointment(@ModelAttribute Appointment appointment, @RequestParam Long patientId) {
        appointmentService.bookAppointment(appointment, patientId);
        return "redirect:/appointments/view";
    }

    // View all appointments
    @GetMapping("/view")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointment/view";
    }

    // Cancel an appointment
    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/appointments/view";
    }
}

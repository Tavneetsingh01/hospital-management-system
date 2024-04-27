package com.taclehealthcare.hms.controllers;


import com.taclehealthcare.hms.configurations.CustomHmsUsersDetails;
import com.taclehealthcare.hms.dao.HmsUsersRepository;
import com.taclehealthcare.hms.dao.PatientsRepository;
import com.taclehealthcare.hms.models.HmsUsers;
import com.taclehealthcare.hms.models.HmsUsersRoles;
import com.taclehealthcare.hms.models.Patients;
import com.taclehealthcare.hms.models.PatientsStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;


@Controller
@RequestMapping(value = "/")
public class PagesController {
    @Autowired
    PatientsRepository patientsRepository;

    @Autowired
    HmsUsersRepository hmsUsersRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // redirect setup for all coming request to main page /
    @GetMapping(value = "")
    public ModelAndView redirectingFromMainUrlToLoginPage() {
        return new ModelAndView("forward:/login");
    }

    @GetMapping(value = "login")
    public String IndexPage() {
        return "MainLoginPage";
    }

    @GetMapping(value = "admin")


    public String AdminPage(@AuthenticationPrincipal CustomHmsUsersDetails adminDetails, Model model, @ModelAttribute("successUserAdd") Object successUserAdd) {
        {

            //the below statement defines a variable userEmail which contains
            //the value returned by the getUsername() function defined in
            //CustomHmsUserDetails here we are referring it via variable adminDetails
            // below statement ony returns one variable containing email of user,
            // and then we can append this variable or we can say model to the route
            //using model.addAttribute

            //String userEmail = adminDetails.getUsername();

            //here below we are just passing the whole CustomUserDetails object
            // which is called adminDetails here ..to the route and then to accesses
            // a particular value from this passed object we can use different
            // functions defined in CustomHmsUsersDetails Class
            //this below passing makes all the available function i the CustomHmsUserDetails
            // Class accessible in view so that we can use any function in our view any time
            // using the object of CustomHmsUsersDetails which is adminDetails here to retrieve
            // data as needed in our view template.

            model.addAttribute("adminHmsUsers", hmsUsersRepository.findAllByRole(HmsUsersRoles.ADMIN));
            model.addAttribute("nursesHmsUsers", hmsUsersRepository.findAllByRole(HmsUsersRoles.NURSE));
            model.addAttribute("staffHmsUsers", hmsUsersRepository.findAllByRole(HmsUsersRoles.MAINTENANCE_STAFF));
            model.addAttribute("doctorsMail", hmsUsersRepository.findAllByRole(HmsUsersRoles.DOCTOR));
            model.addAttribute("allPatients", patientsRepository.findAll());
            model.addAttribute("addHmsUsers", new HmsUsers());
            model.addAttribute("addHmsPatient", new Patients());
            model.addAttribute("redirectionAttribute", successUserAdd);
            model.addAttribute("adminDetails", adminDetails);
            return "AdminPage";
        }
    }

    @GetMapping(value = "doctor")
    public String DoctorsPage(@AuthenticationPrincipal CustomHmsUsersDetails doctorDetails, Model model, @ModelAttribute("successPatientAdd") Object successPatientAdd) {
        model.addAttribute("redirectionAttribute", successPatientAdd);
        model.addAttribute("addHmsPatient", new Patients());
        String doctorEmail = doctorDetails.getUsername();
        model.addAttribute("patients", patientsRepository.findAllByDelegateToDoctor(doctorEmail));
        model.addAttribute("doctorDetails", doctorDetails);
        return "DoctorsPage";
    }

    @GetMapping(value = "nurse")
    public String NursePage(@AuthenticationPrincipal CustomHmsUsersDetails nurseDetails, Model model) {
        model.addAttribute("nurseDetails", nurseDetails);
        return "NursePage";
    }

    @GetMapping(value = "staff")
    public String StaffPage(@AuthenticationPrincipal CustomHmsUsersDetails staffDetails, Model model) {
        model.addAttribute("staffDetails", staffDetails);
        return "StaffPage";
    }

    @GetMapping(value = "aboutus")
    @ResponseBody
    public String AboutUsPage() {
        return "Hello from About us Page";
    }

    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("HH:mm");

    @PostMapping(value = "addAdminUser")
    public ModelAndView AddAdminUser(@ModelAttribute HmsUsers addAdminHmsUser, RedirectAttributes redirectAttributes) throws ParseException {

        // we can also write here as:
        // BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        // intead of @Autowired
        // BCryptPasswordEncoder bCryptPasswordEncoder;

        String encodedPassword = bCryptPasswordEncoder.encode(addAdminHmsUser.getPassword());
        addAdminHmsUser.setPassword(encodedPassword);
        addAdminHmsUser.setRole(HmsUsersRoles.ADMIN);
        hmsUsersRepository.save(addAdminHmsUser);

        redirectAttributes.addFlashAttribute("SuccessUserAdd", "Admin Added Sucessfully");
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(value = "addDoctorUser")
    public ModelAndView AddDoctorUser(@ModelAttribute HmsUsers addDoctorHmsUser, RedirectAttributes redirectAttributes) throws ParseException {


        String encodedPassword = bCryptPasswordEncoder.encode(addDoctorHmsUser.getPassword());
        addDoctorHmsUser.setPassword(encodedPassword);
        addDoctorHmsUser.setRole(HmsUsersRoles.DOCTOR);
        hmsUsersRepository.save(addDoctorHmsUser);

        redirectAttributes.addFlashAttribute("SuccessUserAdd", "Doctor Added Sucessfully");
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(value = "addNurseUser")
    public ModelAndView AddNurseHmsUser(@ModelAttribute HmsUsers addNurseHmsUser, RedirectAttributes redirectAttributes) throws ParseException {


        String encodedPassword = bCryptPasswordEncoder.encode(addNurseHmsUser.getPassword());
        addNurseHmsUser.setPassword(encodedPassword);
        addNurseHmsUser.setRole(HmsUsersRoles.NURSE);
        hmsUsersRepository.save(addNurseHmsUser);

        redirectAttributes.addFlashAttribute("SuccessUserAdd", "Nurse Added Successfully");
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(value = "addStaffUser")
    public ModelAndView AddStaffUser(@ModelAttribute HmsUsers addStaffHmsUser, RedirectAttributes redirectAttributes) throws ParseException {


        String encodedPassword = bCryptPasswordEncoder.encode(addStaffHmsUser.getPassword());
        addStaffHmsUser.setPassword(encodedPassword);
        addStaffHmsUser.setRole(HmsUsersRoles.MAINTENANCE_STAFF);
        hmsUsersRepository.save(addStaffHmsUser);

        redirectAttributes.addFlashAttribute("SuccessUserAdd", "Staff Employee Added Successfully");
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(value = "addPatient")
    public ModelAndView AddPatient(@ModelAttribute Patients addPatient, RedirectAttributes redirectAttributes) throws ParseException {

        patientsRepository.save(addPatient);

        redirectAttributes.addFlashAttribute("SuccessUserAdd", "Patient Added Successfully");
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(value = "addSpecificPatient")
    public ModelAndView AddSpecificPatient(@AuthenticationPrincipal CustomHmsUsersDetails adminDetails, @ModelAttribute Patients addSpecificPatient, RedirectAttributes redirectAttributes) throws ParseException {

        String doctorEmail = adminDetails.getUsername();
        addSpecificPatient.setDelegateToDoctor(doctorEmail);
        patientsRepository.save(addSpecificPatient);

        redirectAttributes.addFlashAttribute("SuccessPatientAdd", "Patient Added Successfully");
        return new ModelAndView("redirect:/doctor");
    }

    /*@GetMapping(value="updateSpecificPatientStatus/{email}")
    public ModelAndView*/

    @PostMapping(value = "updateSpecificPatientStatus/{email}")
    public ModelAndView UpdateSpecificPatientStatus(@PathVariable("email") String email, @ModelAttribute Patients updatePatientStatus, Model model, RedirectAttributes redirectAttributes) {
        PatientsStatus patientStatus = updatePatientStatus.getStatus();
        String patientEmail = email;
        LocalDateTime patientDateAndTime = updatePatientStatus.getDischargedDateTime();
        String Diagnosis = updatePatientStatus.getDiagnosis();
        if (!Diagnosis.isEmpty() && patientStatus != null) {
            patientsRepository.UpdateDiagnosisAndStatus(Diagnosis,patientStatus, patientEmail);
        }
        else if (Diagnosis.isEmpty() && patientStatus != null) {
            patientsRepository.UpdateStatus(patientStatus, patientEmail);
        }
        redirectAttributes.addFlashAttribute("SuccessPatientUpdated", "Patient Details Updated Successfully");
        return new ModelAndView("redirect:/doctor");

    }
    @PostMapping(value = "updatePatientDischargedDateTime/{email}")
    public ModelAndView UpdatePatientDateTime(@PathVariable("email") String email, @ModelAttribute Patients updatePatientStatus, Model model, RedirectAttributes redirectAttributes) {
        String patientEmail = email;
        LocalDateTime patientDateAndTime = updatePatientStatus.getDischargedDateTime();
        String Diagnosis = updatePatientStatus.getDiagnosis();
        patientsRepository.UpdateDschargedDateTime(patientDateAndTime, patientEmail);
        redirectAttributes.addFlashAttribute("SuccessPatientDischarged", "Patient Discharged Date Successfully Added");
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping(value = "deletePatientViaAdmin/{email}")
    public ModelAndView deletePatientViaAdmin(@PathVariable("email") String email, Model model, RedirectAttributes redirectAttributes){
        String patientEmail = email;
        patientsRepository.deletePatientsByEmailEquals(patientEmail);
        return new ModelAndView("redirect:/admin");
    }
    @GetMapping(value = "deletePatientViaDoctor/{email}")
    public ModelAndView deletePatientViaDoctor(@PathVariable("email") String email, Model model, RedirectAttributes redirectAttributes){
        String patientEmail = email;
        patientsRepository.deletePatientsByEmailEquals(patientEmail);
        return new ModelAndView("redirect:/doctor");
    }

}


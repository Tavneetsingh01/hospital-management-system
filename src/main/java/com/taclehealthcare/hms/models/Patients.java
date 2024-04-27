package com.taclehealthcare.hms.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hms_patients", uniqueConstraints = {@UniqueConstraint(name = "hms_patients_unique", columnNames = "email")})
public class Patients {
    @Id
    @SequenceGenerator(name = "hms_patients_sequence", sequenceName = "hms_patients_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hms_patients_sequence")
    private Long id;

    @Column(name = "patient_name", nullable = false, columnDefinition = "TEXT")
    private String patientName;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="mobile_number", nullable = false, length = 30)
    private String mobileNumber;

    @Column(name = "diagnosis", length = 1000)
    private String diagnosis;

    @Column(name = "delegate_to_doctor", nullable = false)
    private String delegateToDoctor;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PatientsStatus status;

    @Column(name ="admitted_date_time")
    private java.time.LocalDateTime admittedDateTime;

    @Column(name ="discharged_date_time")
    private java.time.LocalDateTime dischargedDateTime;


    public Patients() {
    }

    public Patients(String patientName, String email, String mobileNumber, String diagnosis, String delegateToDoctor, PatientsStatus status, LocalDateTime admittedDateTime, LocalDateTime dischargedDateTime) {
        this.patientName = patientName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.diagnosis = diagnosis;
        this.delegateToDoctor = delegateToDoctor;
        this.status = status;
        this.admittedDateTime = admittedDateTime;
        this.dischargedDateTime = dischargedDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDelegateToDoctor() {
        return delegateToDoctor;
    }

    public void setDelegateToDoctor(String delegateToDoctor) {
        this.delegateToDoctor = delegateToDoctor;
    }

    public PatientsStatus getStatus() {
        return status;
    }

    public void setStatus(PatientsStatus status) {
        this.status = status;
    }

    public LocalDateTime getAdmittedDateTime() {
      return admittedDateTime;
    }

    public void setAdmittedDateTime(String admittedDateTime) {
        this.admittedDateTime = LocalDateTime.parse(admittedDateTime);
    }

    public LocalDateTime getDischargedDateTime() {
        return dischargedDateTime;
    }

    public void setDischargedDateTime(String dischargedDateTime) {
        this.dischargedDateTime = LocalDateTime.parse(dischargedDateTime);
    }
}
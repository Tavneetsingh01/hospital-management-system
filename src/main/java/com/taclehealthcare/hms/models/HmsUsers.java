package com.taclehealthcare.hms.models;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "hms_users", uniqueConstraints = {@UniqueConstraint(name = "hms_users_email_unique", columnNames = "email")})
public class HmsUsers {
    @Id
    @SequenceGenerator(name = "hms_users_sequence", sequenceName = "hms_users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hms_users_sequence")
    private Long id;

    @Column(name = "user_name", nullable = false, columnDefinition = "TEXT")
    private String userName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false, columnDefinition="TEXT")
    @Enumerated(EnumType.STRING)
    private HmsUsersRoles role;

    @Column(name = "mobile_number", nullable = false, length = 50)
    private String mobileNumber;

    @Column(name = "time_shift_start")
    @Temporal(TemporalType.TIME)
    private Date timeShiftStart;

    @Column(name = "time_shift_end")
    @Temporal(TemporalType.TIME)
    private Date timeShiftEnd;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "staff_category", columnDefinition="TEXT")
    private String staffCategory;

    //Default constructor
    public HmsUsers() {
    }

    //Parameterizes Constructor
    public HmsUsers(String userName, String email, String password, HmsUsersRoles role, String mobileNumber, Date timeShiftStart, Date timeShiftEnd, String speciality, String staffCategory) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.mobileNumber = mobileNumber;
        this.timeShiftStart = timeShiftStart;
        this.timeShiftEnd = timeShiftEnd;
        this.speciality = speciality;
        this.staffCategory = staffCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HmsUsersRoles getRole() {
        return role;
    }

    public void setRole(HmsUsersRoles role) {
        this.role = role;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getTimeShiftStart() {
        return timeShiftStart;
    }


    public void setTimeShiftStart(String timeShiftStart) throws ParseException {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("HH:mm");
        this.timeShiftStart = DATE_TIME_FORMAT.parse(timeShiftStart);
    }

    public Date getTimeShiftEnd() {
        return timeShiftEnd;
    }

    public void setTimeShiftEnd(String timeShiftEnd) throws ParseException {
        SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("HH:mm");
        this.timeShiftEnd = DATE_TIME_FORMAT.parse(timeShiftEnd);
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getStaffCatagory() {
        return staffCategory;
    }

    public void setStaffCatagory(String staffCategory) {
        this.staffCategory = staffCategory;
    }
}

package com.taclehealthcare.hms.dao;

import com.taclehealthcare.hms.models.Patients;
import com.taclehealthcare.hms.models.PatientsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {

    List<Patients> findAllByDelegateToDoctor(String email);

    @Transactional
    @Modifying
    @Query("update Patients u set u.status = :status where u.email = :email")
    void UpdateStatus(@Param("status") PatientsStatus status, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("update Patients u set u.diagnosis = :diagnosis , u.status = :status where u.email = :email")
    void UpdateDiagnosisAndStatus(@Param("diagnosis") String diagnosis, @Param("status") PatientsStatus patientStatus, @Param("email") String patientEmail);

    @Transactional
    @Modifying
    @Query("update Patients u set u.dischargedDateTime = :dischargedDateTime where u.email = :email")
    void UpdateDschargedDateTime(@Param("dischargedDateTime") LocalDateTime dischargedDateTime, @Param("email") String patientEmail);

    @Transactional
    @Modifying
    void deletePatientsByEmailEquals(String email);
}

package com.taclehealthcare.hms.dao;

import com.taclehealthcare.hms.models.HmsUsers;
import com.taclehealthcare.hms.models.HmsUsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HmsUsersRepository extends JpaRepository<HmsUsers,Long>{
    @Query("Select u from HmsUsers u where u.email = :email")
    public HmsUsers findByEmail(@Param("email") String email);

    List<HmsUsers> findAllByRole(HmsUsersRoles role);
}
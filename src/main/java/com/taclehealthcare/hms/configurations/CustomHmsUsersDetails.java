package com.taclehealthcare.hms.configurations;

/*import com.taclehealthcare.hms.dao.HmsUsersRepository;*/

import com.taclehealthcare.hms.models.HmsUsers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomHmsUsersDetails implements UserDetails {

    private HmsUsers hmsUsers;

    public CustomHmsUsersDetails(HmsUsers hmsUsers) {
        super();
        this.hmsUsers = hmsUsers;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority SimpleGrantedAuthority = new SimpleGrantedAuthority(String.valueOf(hmsUsers.getRole()));
        return List.of(SimpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return hmsUsers.getPassword();
    }

    // Here our username is email getUsername() function name is predefined inUser Details class
    // To this function we only have to return our email with which it checks what is entered email and password
    // by user is valid or not as here in our project email is unique
    @Override
    public String getUsername() {
        return hmsUsers.getEmail();
    }

    public String getRole() {
        return String.valueOf(hmsUsers.getRole());
    }

    public String getFullUserName() {
        return hmsUsers.getUserName();
    }

    public String getStaffCatagory() {
        return hmsUsers.getStaffCatagory();
    }

    public String getTimeShiftStart() {
        return String.valueOf(hmsUsers.getTimeShiftStart());
    }

    public String getTimeShiftEnd() {
        return String.valueOf(hmsUsers.getTimeShiftEnd());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

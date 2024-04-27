package com.taclehealthcare.hms.configurations;

import com.taclehealthcare.hms.dao.HmsUsersRepository;
import com.taclehealthcare.hms.models.HmsUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class HmsUsersDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private HmsUsersRepository hmsUsersRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // username in method loadUserByUsername(string username) is email, so I have replaced email in (string email)
        // parameter by (string email) here email is just a parameter or variable of type String
        HmsUsers hmsUser = hmsUsersRepository.findByEmail(email);
        if(email == null){
            throw new UsernameNotFoundException("User Doesn't Exist");
        }
        CustomHmsUsersDetails customHmsUsersDetails = new CustomHmsUsersDetails(hmsUser);
        return customHmsUsersDetails;
    }
}

package com.taclehealthcare.hms.configurations;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomHmsUsersDetails customHmsUsersDetails = (CustomHmsUsersDetails) authentication.getPrincipal();
        String redirectURL = request.getContextPath();
        if (Objects.equals(customHmsUsersDetails.getRole(), "ADMIN")) {
            redirectURL = "admin";
        } else if (Objects.equals(customHmsUsersDetails.getRole(), "DOCTOR")) {
            redirectURL = "doctor";
        } else if (Objects.equals(customHmsUsersDetails.getRole(), "NURSE")) {
            redirectURL = "nurse";
        } else if (Objects.equals(customHmsUsersDetails.getRole(), "MAINTENANCE_STAFF")) {
            redirectURL = "staff";
        }
        response.sendRedirect(redirectURL);
    }
}

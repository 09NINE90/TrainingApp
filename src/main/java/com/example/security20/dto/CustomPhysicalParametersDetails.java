package com.example.security20.dto;

import com.example.security20.entity.UserPhysicalParameters;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomPhysicalParametersDetails implements UserDetails {
    private final UserPhysicalParameters userPhysicalParameters;

    public CustomPhysicalParametersDetails(UserPhysicalParameters userPhysicalParameters){
        this.userPhysicalParameters = userPhysicalParameters;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public int getAge() {
        return userPhysicalParameters.getAge();
    }

    public double getWeight() {
        return userPhysicalParameters.getWeight();
    }
    public double getHeight() {
        return userPhysicalParameters.getHeight();
    }
    public double getArmCircumference() {
        return userPhysicalParameters.getArmCircumference();
    }
    public double getHipCircumference() {
        return userPhysicalParameters.getHipCircumference();
    }
    public double getWaistCircumference() {
        return userPhysicalParameters.getWaistCircumference();
    }
    public String getDate() {
        return userPhysicalParameters.getDate();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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

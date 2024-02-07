package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Table(name = "security_user")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String roles = "ROLE_USER";
    @OneToOne
    @JoinColumn(name = "physicalParameters_id", referencedColumnName = "id")
    private UserPhysicalParameters userPhysicalParameters;
}

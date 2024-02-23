package com.exampleapp.heroWars.model.dto;

import com.exampleapp.heroWars.model.Role;
import lombok.Data;

@Data
public class RegisterDTO   {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Role role;

}

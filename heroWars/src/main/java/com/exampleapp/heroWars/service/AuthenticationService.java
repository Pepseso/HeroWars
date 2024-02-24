package com.exampleapp.heroWars.service;

import com.exampleapp.heroWars.exception.registration.PasswordTooShortException;
import com.exampleapp.heroWars.exception.registration.RegistrationDataMissingException;
import com.exampleapp.heroWars.exception.registration.UsernameAlreadyExistsException;
import com.exampleapp.heroWars.exception.registration.WrongFromatOfUsernameException;
import com.exampleapp.heroWars.model.dto.AuthenticationResponse;
import com.exampleapp.heroWars.model.User;
import com.exampleapp.heroWars.model.dto.LoginDTO;
import com.exampleapp.heroWars.model.dto.RegisterDTO;
import com.exampleapp.heroWars.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.*;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register (RegisterDTO request){
        validateRegistrationData(request);
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user = repository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);

    }

    private void validateRegistrationData(RegisterDTO request){
        if(missingRegisterData(request)){
            throw new RegistrationDataMissingException();
        }
        if(repository.findByUsername(request.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException();
        }
        if(request.getPassword().length() < 4){
            throw new PasswordTooShortException();
        }
        if (!checkIfUsernameContainsLettersAndNumbersOnly(request.getUsername())){
            throw new WrongFromatOfUsernameException();
        }
    }

    private boolean checkIfUsernameContainsLettersAndNumbersOnly(String username) {
        String pattern = "^[a-zA-Z0-9]*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(username);
        return matcher.matches();
    }

    private boolean missingRegisterData( RegisterDTO request){
        return request.getUsername().isBlank()
                || request.getFirstname().isBlank()
                || request.getLastname().isBlank()
                || request.getPassword().isBlank();
    }
}

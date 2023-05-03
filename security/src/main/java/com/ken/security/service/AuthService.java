package com.ken.security.service;

import com.ken.security.entity.Student;
import com.ken.security.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private JWTService jwtService;
    public Student saveStudent(Student student){
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public String generateToken(Student student){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(student.getEmail(), student.getPassword()));
        return jwtService.generateToken(student);
    }

    public String validate(String token){
        boolean isValid = jwtService.validateToken(token);
        if(isValid)
            return "Is valid";
        else
            return "is not valid";
    }
}

package com.ken.security.controller;

import com.ken.security.entity.Student;
import com.ken.security.service.AuthService;
import com.ken.security.service.TokenR;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody Student student){
        return ResponseEntity.ok(authService.saveStudent(student));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student student){
       return ResponseEntity.ok(authService.generateToken(student));
    }

    @PostMapping("/validate")
    public String validate(@RequestBody TokenR token){
        return authService.validate(token.token());
    }
}

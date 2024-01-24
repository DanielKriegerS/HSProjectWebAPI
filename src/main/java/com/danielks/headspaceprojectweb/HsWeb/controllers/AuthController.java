package com.danielks.headspaceprojectweb.HsWeb.controllers;

import com.danielks.headspaceprojectweb.HsWeb.models.AuthDTO;
import com.danielks.headspaceprojectweb.HsWeb.models.RegisterDTO;
import com.danielks.headspaceprojectweb.HsWeb.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.danielks.headspaceprojectweb.HsWeb.entities.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.userLogin(), data.passHash());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByuserLogin(data.userLogin()) != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Usuário já registrado");
            return ResponseEntity.badRequest().body(response);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.passHash());
        User newUser = new User(data.userLogin(), encryptedPassword, data.role());

        try {
            User savedUser = this.repository.save(newUser);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("userId", savedUser.getId());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro durante o registro");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



}

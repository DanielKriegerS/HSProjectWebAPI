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
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByuserLogin(data.userLogin()) != null) {
            return ResponseEntity.badRequest().body("Usuário já registrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.passHash());
        User newUser = new User(data.userLogin(), encryptedPassword, data.role());

        try {
            this.repository.save(newUser);
            return ResponseEntity.ok().body("Usuário registrado com sucesso. ID: " + newUser.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante o registro");
        }
    }
}

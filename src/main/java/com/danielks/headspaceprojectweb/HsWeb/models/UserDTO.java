package com.danielks.headspaceprojectweb.HsWeb.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    @NotBlank(message = "O nome não pode estar em branco!")
    private String userName;
    @NotBlank(message = "A senha não pode estar em branco!")
    private String passHash;
    @NotBlank(message = "O telefone não pode estar em branco!")
    private Long phone;
    @NotBlank(message = "O endereço não pode estar em branco!")
    private String adress;
    @NotBlank(message = "O email não pode estar em branco!")
    private String email;
    @NotBlank(message = "A idade não pode estar em branco!")
    private int age;
    @NotNull
    private String User_type;
    private LocalDateTime create_time;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUser_type() {
        return User_type;
    }

    public void setUser_type(String User_type) {
        this.User_type = User_type;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }
}

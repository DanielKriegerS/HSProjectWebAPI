package com.danielks.headspaceprojectweb.HsWeb.entities;

import com.danielks.headspaceprojectweb.HsWeb.entities.roles.UserRoles;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String userName;
    private String userLogin;
    private String passHash;
    private Long phone;
    private UUID address;
    private String email;
    private int age;
    private LocalDateTime create_time;
    @Enumerated(EnumType.STRING)
    private UserRoles user_role;


    public User() {
    }

    public User(String userLogin, String passHash, UserRoles user_role) {
        this.userLogin = userLogin;
        this.passHash = passHash;
        this.user_role = user_role;
    }

    public User(UUID id, String userName, String userLogin, String passHash, Long phone, UUID address, String email, int age, LocalDateTime create_time, UserRoles user_role) {
        this.id = id;
        this.userName = userName;
        this.passHash = passHash;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.age = age;
        this.create_time = create_time;
        this.user_role = user_role;
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

    public UUID getAddress() {
        return address;
    }

    public void setAddress(UUID address) {
        this.address = address;
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

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public UserRoles getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRoles user_role) {
        this.user_role = user_role;
    }

    private static final Map<UserRoles, List<String>> ROLE_PERMISSIONS_MAP = Map.of(
            UserRoles.ADMIN, Arrays.asList("ROLE_ADMIN", "ROLE_PAYINGUSER", "ROLE_STANDARDUSER"),
            UserRoles.PAYINGUSER, Arrays.asList("ROLE_PAYINGUSER", "ROLE_STANDARDUSER"),
            UserRoles.STANDARDUSER, Collections.singletonList("ROLE_STANDARDUSER"),
            UserRoles.USER, Collections.singletonList("ROLE_USER")
    );

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authorities = ROLE_PERMISSIONS_MAP.getOrDefault(this.user_role, Collections.emptyList());
        return translateToGrantedAuthorities(authorities);
    }

    private Collection<? extends GrantedAuthority> translateToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
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

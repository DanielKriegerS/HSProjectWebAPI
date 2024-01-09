package com.danielks.headspaceprojectweb.HsWeb.repositories;

import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID>{
    Optional<User> findByUserName(String userName);
    UserDetails findByUserLogin(String userLogin);
}

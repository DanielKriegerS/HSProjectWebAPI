package com.danielks.headspaceprojectweb.HsWeb.repositories;

import com.danielks.headspaceprojectweb.HsWeb.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}

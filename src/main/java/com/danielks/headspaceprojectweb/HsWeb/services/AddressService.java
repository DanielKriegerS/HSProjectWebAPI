package com.danielks.headspaceprojectweb.HsWeb.services;

import com.danielks.headspaceprojectweb.HsWeb.models.AddressDTO;
import com.danielks.headspaceprojectweb.HsWeb.repositories.AddressRepository;
import com.danielks.headspaceprojectweb.HsWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;


}

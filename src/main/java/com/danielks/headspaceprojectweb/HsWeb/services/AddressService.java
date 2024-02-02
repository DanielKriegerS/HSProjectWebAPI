package com.danielks.headspaceprojectweb.HsWeb.services;

import com.danielks.headspaceprojectweb.HsWeb.entities.Address;
import com.danielks.headspaceprojectweb.HsWeb.entities.Post;
import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.InvalidRequestException;
import com.danielks.headspaceprojectweb.HsWeb.models.AddressDTO;
import com.danielks.headspaceprojectweb.HsWeb.models.UserDTO;
import com.danielks.headspaceprojectweb.HsWeb.repositories.AddressRepository;
import com.danielks.headspaceprojectweb.HsWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    private AddressDTO convertToDTO(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getUserId(),
                address.getNumber(),
                address.getDistrict(),
                address.getStreet(),
                address.getCep()
        );
    }

    private Address convertToEntity(AddressDTO addressDTO){
        return new Address(
                addressDTO.id(),
                addressDTO.userId(),
                addressDTO.number(),
                addressDTO.district(),
                addressDTO.street(),
                addressDTO.cep()
        );
    }


    public Optional<AddressDTO> getAddressByUserId(UUID userId) throws Exception {
        Optional<Address> optionalAddress = addressRepository.findByUserId(userId);

        if(optionalAddress.isPresent()){
            return optionalAddress.map(this::convertToDTO);
        } else {
            throw new Exception("Erro genérico!");
        }
    }

    public AddressDTO createAddress(AddressDTO addressDTO) {
        if (addressDTO.number() == 0 ||
            addressDTO.cep() == null ||
            addressDTO.street() == null) {
            throw new InvalidRequestException("Number, cep or street cannot be null for creating a address");
        }

        Address address = convertToEntity(addressDTO);

        address = addressRepository.save(address);
        return convertToDTO(address);
    }
}

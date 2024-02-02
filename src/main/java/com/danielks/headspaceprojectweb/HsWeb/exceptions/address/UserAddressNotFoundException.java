package com.danielks.headspaceprojectweb.HsWeb.exceptions.address;

import com.danielks.headspaceprojectweb.HsWeb.exceptions.EntityNotFoundException;

import java.util.UUID;

public class UserAddressNotFoundException extends EntityNotFoundException {
    public UserAddressNotFoundException(UUID userId, UUID id) {
        super("User with id " + userId + " not found, or address with id " + id + " not found.");
    }
}

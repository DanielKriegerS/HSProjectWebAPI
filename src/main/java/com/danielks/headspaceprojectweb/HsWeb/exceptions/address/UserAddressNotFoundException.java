package com.danielks.headspaceprojectweb.HsWeb.exceptions.address;

import com.danielks.headspaceprojectweb.HsWeb.exceptions.EntityNotFoundException;

import java.util.UUID;

public class UserAddressNotFoundException extends EntityNotFoundException {
    public UserAddressNotFoundException(UUID userId) {
        super("Address of user with id " + userId + " not found.");
    }
}

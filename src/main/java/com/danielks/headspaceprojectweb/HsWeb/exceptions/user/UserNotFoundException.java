package com.danielks.headspaceprojectweb.HsWeb.exceptions.user;

import com.danielks.headspaceprojectweb.HsWeb.exceptions.EntityNotFoundException;

import java.util.UUID;

public class UserNotFoundException extends EntityNotFoundException {
        public UserNotFoundException(UUID userId) {
            super("User with id " + userId + " not found");
        }
}

package com.danielks.headspaceprojectweb.HsWeb.exceptions;

import com.sun.jdi.request.InvalidRequestStateException;


public class InvalidRequestException extends InvalidRequestStateException {
        public InvalidRequestException(String message) {
        super(message);
    }
}

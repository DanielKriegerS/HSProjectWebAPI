package com.danielks.headspaceprojectweb.HsWeb.exceptions.post;

import com.danielks.headspaceprojectweb.HsWeb.exceptions.EntityNotFoundException;

import java.util.UUID;

public class PostNotFoundException extends EntityNotFoundException {
    public PostNotFoundException(UUID postId) {
        super("Post with id " + postId + " not found");
    }
}

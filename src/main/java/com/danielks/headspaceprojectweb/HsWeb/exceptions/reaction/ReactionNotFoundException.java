package com.danielks.headspaceprojectweb.HsWeb.exceptions.reaction;

import com.danielks.headspaceprojectweb.HsWeb.exceptions.EntityNotFoundException;

import java.util.UUID;

public class ReactionNotFoundException extends EntityNotFoundException{
    public ReactionNotFoundException(UUID reactionId) {
            super("Reaction with id " + reactionId + " not found");
        }
    }


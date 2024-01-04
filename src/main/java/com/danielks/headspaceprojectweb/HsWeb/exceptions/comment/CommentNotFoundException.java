package com.danielks.headspaceprojectweb.HsWeb.exceptions.comment;


import com.danielks.headspaceprojectweb.HsWeb.exceptions.EntityNotFoundException;

import java.util.UUID;

public class CommentNotFoundException extends EntityNotFoundException {
     public CommentNotFoundException(UUID commentId) {
         super("Comment with id " + commentId + " not found");
     }
 }


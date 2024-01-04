package com.danielks.headspaceprojectweb.HsWeb.entities.user_types;

import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import com.danielks.headspaceprojectweb.HsWeb.models.CommentDTO;
import com.danielks.headspaceprojectweb.HsWeb.models.PostDTO;
import com.danielks.headspaceprojectweb.HsWeb.models.ReactionDTO;
import com.danielks.headspaceprojectweb.HsWeb.services.CommentService;
import com.danielks.headspaceprojectweb.HsWeb.services.PostService;
import com.danielks.headspaceprojectweb.HsWeb.services.ReactionService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("visitor")
public class Visitor extends User {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReactionService reactionService;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Override
    public UUID getId() {
        return super.getId();
    }
    public List<PostDTO> viewAllPosts() {
        return postService.getAllPosts();
    }

    public List<CommentDTO> viewAllComments() {
        return commentService.getAllComments();
    }

    public List<ReactionDTO> viewAllReactions() {
        return reactionService.getAllReactions();
    }

}

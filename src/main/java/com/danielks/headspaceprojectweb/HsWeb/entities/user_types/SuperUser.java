package com.danielks.headspaceprojectweb.HsWeb.entities.user_types;

import com.danielks.headspaceprojectweb.HsWeb.entities.Comment;
import com.danielks.headspaceprojectweb.HsWeb.entities.Post;
import com.danielks.headspaceprojectweb.HsWeb.entities.User;
import com.danielks.headspaceprojectweb.HsWeb.entities.user_actions.UserActions;
import com.danielks.headspaceprojectweb.HsWeb.models.CommentDTO;
import com.danielks.headspaceprojectweb.HsWeb.models.PostDTO;
import com.danielks.headspaceprojectweb.HsWeb.models.ReactionDTO;
import com.danielks.headspaceprojectweb.HsWeb.services.CommentService;
import com.danielks.headspaceprojectweb.HsWeb.services.PostService;
import com.danielks.headspaceprojectweb.HsWeb.services.ReactionService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@DiscriminatorValue("superuser")
public class SuperUser extends User implements UserActions {
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

    public void createPost(String postHeader, String postDesc, String postBody){
        PostDTO postDTO = new PostDTO();
        postDTO.setUser(this);
        postDTO.setHeader(postHeader);
        postDTO.setDesc(postDesc);
        postDTO.setBody(postBody);
        postDTO.setCreate_time(LocalDate.now());

        postService.createPost(postDTO);
    }

    public void updatePost(UUID postId, String updatedPostHeader, String updatedPostDesc, String updatedPostBody) {
        PostDTO updatedPostDTO = new PostDTO();

        updatedPostDTO.setHeader(updatedPostHeader != null ? updatedPostHeader : "");
        updatedPostDTO.setDesc(updatedPostDesc != null ? updatedPostDesc : "");
        updatedPostDTO.setBody(updatedPostBody != null ? updatedPostBody : "");

        postService.updatePost(postId, updatedPostDTO);
    }

    public void deletePost(UUID postId) {
        postService.deletePost(postId);
    }

    @Override
    public void commentOnPost(Post post, String commentText) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setUser(this);
        commentDTO.setPost(post);
        commentDTO.setBody(commentText);
        commentDTO.setCreate_time(LocalDate.now());

        commentService.createComment(commentDTO);
    }

    @Override
    public void updateComment(UUID commentId, String updatedCommentText) {
        CommentDTO updatedCommentDTO = new CommentDTO();
        updatedCommentDTO.setBody(updatedCommentText);

        commentService.updateComment(commentId, updatedCommentDTO);
    }
    @Override
    public void deleteComment(UUID commentId) {
        commentService.deleteComment(commentId);
    }
    @Override
    public void reactToPost(Post post, String reactType) {
        ReactionDTO reactionDTO = new ReactionDTO();
        reactionDTO.setUser(this);
        reactionDTO.setEntityId(post.getId());
        reactionDTO.setEntity_type("post");
        reactionDTO.setReact_type(reactType);
        reactionDTO.setCreate_time(LocalDate.now());

        reactionService.createReaction(reactionDTO);
    }
    @Override
    public void reactToComment(Comment comment, String reactType) {
        ReactionDTO reactionDTO = new ReactionDTO();
        reactionDTO.setUser(this);
        reactionDTO.setEntityId(comment.getId());
        reactionDTO.setEntity_type("comment");
        reactionDTO.setReact_type(reactType);
        reactionDTO.setCreate_time(LocalDate.now());

        reactionService.createReaction(reactionDTO);
    }

    @Override
    public void updateReaction(UUID reactionId, String updatedReactType) {
        ReactionDTO updatedReactionDTO = new ReactionDTO();
        updatedReactionDTO.setReact_type(updatedReactType);

        ReactionDTO updatedReaction = reactionService.updateReactionType(reactionId, updatedReactType);

        if (updatedReaction != null) {
            // Reação atualizada com sucesso
            // Você pode tratar o resultado aqui se necessário
        } else {
            // Lidar com o caso de falha na atualização da reação
        }
    }

    @Override
    public void deleteReaction(UUID reactionId) {
        reactionService.deleteReaction(reactionId);
    }
}

package com.danielks.headspaceprojectweb.HsWeb.services;

import com.danielks.headspaceprojectweb.HsWeb.entities.Comment;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.InvalidRequestException;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.comment.CommentNotFoundException;
import com.danielks.headspaceprojectweb.HsWeb.models.CommentDTO;
import com.danielks.headspaceprojectweb.HsWeb.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    private CommentDTO convertToDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getUser(),
                comment.getPost(),
                comment.getBody(),
                comment.getCreate_time()
        );
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        return new Comment(
                commentDTO.id(),
                commentDTO.user(),
                commentDTO.post(),
                commentDTO.body(),
                commentDTO.create_time()
        );
    }

    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CommentDTO> getCommentById(UUID id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);

        if (commentOptional.isPresent()) {
            return commentOptional.map(this::convertToDTO);
        } else {
            throw new CommentNotFoundException(id);
        }
    }

    public List<CommentDTO> commentsByPostId(UUID postId) {
        List<Comment> comments = commentRepository.commentsByPostId(postId);
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        if (commentDTO.body() == null || commentDTO.user() == null || commentDTO.post() == null) {
            throw new InvalidRequestException("Body, user, or post cannot be null for creating a comment");
        }

        Comment comment = convertToEntity(commentDTO);
        comment.setCreate_time(LocalDate.now());

        comment = commentRepository.save(comment);
        return convertToDTO(comment);
    }

    public CommentDTO updateComment(UUID id, CommentDTO updatedCommentDTO) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();

            if (updatedCommentDTO.body() != null) {
                existingComment.setBody(updatedCommentDTO.body());
            }

            existingComment = commentRepository.save(existingComment);
            return convertToDTO(existingComment);
        } else {
            throw new InvalidRequestException("Comment with ID " + id + " does not exist");
        }
    }

    public long countCommentsByPostId(UUID postId) {
        return commentRepository.countByPostId(postId);
    }

    public void deleteComment(UUID id) {
        commentRepository.deleteById(id);
    }
}

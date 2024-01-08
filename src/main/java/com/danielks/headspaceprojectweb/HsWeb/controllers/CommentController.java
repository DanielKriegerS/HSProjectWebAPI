package com.danielks.headspaceprojectweb.HsWeb.controllers;

import com.danielks.headspaceprojectweb.HsWeb.models.CommentDTO;
import com.danielks.headspaceprojectweb.HsWeb.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService ;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable UUID id) {
        Optional<CommentDTO> comment = commentService.getCommentById(id);

        if (comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/commentsByPostId/{postId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable UUID postId) {
        List<CommentDTO> comments = commentService.commentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO CommentDTO) {
        CommentDTO createdComment = commentService.createComment(CommentDTO);

        if (createdComment != null) {
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable UUID id, @RequestBody CommentDTO updatedCommentDTO) {
        CommentDTO updatedComment = commentService.updateComment(id, updatedCommentDTO);

        if (updatedComment != null) {
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        boolean CommentExists = commentService.getCommentById(id).isPresent();

        if (CommentExists) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

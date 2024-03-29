package com.danielks.headspaceprojectweb.HsWeb.repositories;

import com.danielks.headspaceprojectweb.HsWeb.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    long countByPostId(UUID postId);
    @Query("SELECT c FROM Comment c WHERE c.post.id = :postId")
    List<Comment> commentsByPostId(@Param("postId") UUID postId);
}

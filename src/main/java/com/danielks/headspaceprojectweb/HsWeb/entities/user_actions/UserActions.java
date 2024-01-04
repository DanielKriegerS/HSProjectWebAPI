package com.danielks.headspaceprojectweb.HsWeb.entities.user_actions;

import com.danielks.headspaceprojectweb.HsWeb.entities.Comment;
import com.danielks.headspaceprojectweb.HsWeb.entities.Post;

import java.util.UUID;

public interface UserActions {

        void commentOnPost(Post post, String commentText);
        void updateComment(UUID commentId, String updatedCommentText);
        void deleteComment(UUID commentId);
        void reactToPost(Post post, String reactType);
        void reactToComment(Comment comment, String reactType);
        void updateReaction(UUID reactionId, String updatedReactType);
        void deleteReaction(UUID reactionId);

}

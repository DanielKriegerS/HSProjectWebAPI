package com.danielks.headspaceprojectweb.HsWeb.services;

import com.danielks.headspaceprojectweb.HsWeb.entities.Post;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.InvalidRequestException;
import com.danielks.headspaceprojectweb.HsWeb.exceptions.post.PostNotFoundException;
import com.danielks.headspaceprojectweb.HsWeb.models.PostDTO;
import com.danielks.headspaceprojectweb.HsWeb.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setUser(post.getUser());
        postDTO.setHeader(post.getHeader());
        postDTO.setDesc(post.getDesc());
        postDTO.setBody(post.getBody());
        postDTO.setCreate_time(post.getCreate_time());
        return postDTO;
    }

    private Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setUser(postDTO.getUser());
        post.setHeader(postDTO.getHeader());
        post.setDesc(postDTO.getDesc());
        post.setBody(postDTO.getBody());
        post.setCreate_time(postDTO.getCreate_time());
        return post;
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PostDTO> getPostById(UUID id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            return postOptional.map(this::convertToDTO);
        } else {
            throw new PostNotFoundException(id);
        }
    }

    public PostDTO createPost(PostDTO postDTO) {
        if (postDTO.getHeader() == null || postDTO.getDesc() == null || postDTO.getBody() == null) {
            throw new InvalidRequestException("Header, Description, or Body cannot be null for creating a post");
        }

        Post post = convertToEntity(postDTO);
        post.setCreate_time(LocalDate.now());

        post = (Post) postRepository.save(post);
        return convertToDTO(post);
    }

    public PostDTO updatePost(UUID id, PostDTO updatedPostDTO) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            if (updatedPostDTO.getHeader() != null) {
                existingPost.setHeader(updatedPostDTO.getHeader());
            }
            if (updatedPostDTO.getDesc() != null) {
                existingPost.setDesc(updatedPostDTO.getDesc());
            }
            if (updatedPostDTO.getBody() != null) {
                existingPost.setBody(updatedPostDTO.getBody());
            }

            existingPost = (Post) postRepository.save(existingPost);
            return convertToDTO(existingPost);
        } else {
            throw new PostNotFoundException(id);
        }
    }
    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }


}

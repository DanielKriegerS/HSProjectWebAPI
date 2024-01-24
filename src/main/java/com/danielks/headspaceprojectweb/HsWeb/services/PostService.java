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
        return new PostDTO(
                post.getId(),
                post.getUserId(),
                post.getHeader(),
                post.getDesc(),
                post.getBody(),
                post.getCreate_time()
        );
    }

    private Post convertToEntity(PostDTO postDTO) {
        return new Post(
                postDTO.id(),
                postDTO.userId(),
                postDTO.header(),
                postDTO.desc(),
                postDTO.body(),
                postDTO.create_time()
        );
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
        if (postDTO.header() == null || postDTO.desc() == null || postDTO.body() == null) {
            throw new InvalidRequestException("Header, Description, or Body cannot be null for creating a post");
        }

        Post post = convertToEntity(postDTO);
        post.setCreate_time(LocalDate.now());

        post = postRepository.save(post);
        return convertToDTO(post);
    }

    public PostDTO updatePost(UUID id, PostDTO updatedPostDTO) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            if (updatedPostDTO.header() != null) {
                existingPost.setHeader(updatedPostDTO.header());
            }
            if (updatedPostDTO.desc() != null) {
                existingPost.setDesc(updatedPostDTO.desc());
            }
            if (updatedPostDTO.body() != null) {
                existingPost.setBody(updatedPostDTO.body());
            }

            existingPost = postRepository.save(existingPost);
            return convertToDTO(existingPost);
        } else {
            throw new PostNotFoundException(id);
        }
    }

    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }

    public List<PostDTO> getPostsByUserId(UUID userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

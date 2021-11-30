package com.gucardev.folksdevblogbe.service;


import com.gucardev.folksdevblogbe.exception.PostNotFoundException;
import com.gucardev.folksdevblogbe.model.Post;
import com.gucardev.folksdevblogbe.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }


    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Not found!"));
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePostById(Long id, Post post) {
        Post existing = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Not found!"));
        return postRepository
                .save(updatePost(existing, post));
    }

    public Post deletePostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Not found!"));
        //soft delete
        post.setDeleted(true);
        return postRepository.save(post);
    }

    protected Post updatePost(Post existing, Post post) {
        existing.setName(post.getName());
        existing.setDetails(post.getDetails());
        existing.setImageUrl(post.getImageUrl());
        existing.setVideoUrl(post.getVideoUrl());
        return existing;
    }


}

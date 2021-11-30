package com.gucardev.folksdevblogbe;

import com.gucardev.folksdevblogbe.controller.dto.PostDto;
import com.gucardev.folksdevblogbe.model.Post;

public class TestSupport {

    public Post generateBlogPost(Long id, String name, String details) {
        return new Post.Builder()
                .id(id)
                .name(name)
                .details(details)
                .build();
    }

    public PostDto generateBlogPostDto(Long id, String name, String details) {
        return new PostDto.Builder()
                .id(id)
                .name(name)
                .details(details)
                .build();
    }

}

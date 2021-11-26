package com.gucardev.folksdevblogbe;

import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.model.BlogPost;

public class TestSupport {

    public BlogPost generateBlogPost(Long id, String name, String details) {
        return new BlogPost.Builder()
                .id(id)
                .name(name)
                .details(details)
                .build();
    }

    public BlogPostDto generateBlogPostDto(Long id, String name, String details) {
        return new BlogPostDto.Builder()
                .id(id)
                .name(name)
                .details(details)
                .build();
    }

}

package com.gucardev.folksdevblogbe.dto.converter;

import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.model.BlogPost;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BlogPostDtoConverter {


    public BlogPostDto blogPostToDto(BlogPost blogPost) {
        return new BlogPostDto.Builder()
                .id(blogPost.getId())
                .name(blogPost.getName())
                .details(blogPost.getDetails())
                .imageUrl(blogPost.getImageUrl())
                .videoUrl(blogPost.getVideoUrl())
                .created(blogPost.getCreated())
                .deleted(blogPost.isDeleted())
                .build();
    }


    public BlogPost dtoToBlogPost(BlogPostDto blogPostDto) {
        return new BlogPost.Builder()
                .id(blogPostDto.getId())
                .name(blogPostDto.getName())
                .details(blogPostDto.getDetails())
                .imageUrl(blogPostDto.getImageUrl())
                .videoUrl(blogPostDto.getVideoUrl())
                .deleted(blogPostDto.isDeleted())
                .build();
    }

}

package com.gucardev.folksdevblogbe.dto.converter;

import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.model.BlogPost;
import com.gucardev.folksdevblogbe.repository.BlogPostRepository;
import com.gucardev.folksdevblogbe.service.BlogPostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BlogPostDtoConverterTest {

    private BlogPostDtoConverter blogPostDtoConverter;


    @BeforeEach
    void setup() {
        blogPostDtoConverter = new BlogPostDtoConverter();
    }


    @Test
    public void test_convertBlogPostToDto() {
        BlogPost blogPost = new BlogPost.Builder()
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        BlogPostDto blogPostDto = new BlogPostDto.Builder()
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        BlogPostDto expected = blogPostDtoConverter.blogPostToDto(blogPost);

        Assertions.assertEquals(expected, blogPostDto);

    }


    @Test
    public void test_convertDtoToBlogPost() {

        BlogPostDto blogPostDto = new BlogPostDto.Builder()
                .id(1L)
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        BlogPost blogPost = new BlogPost.Builder()
                .id(1L)
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        BlogPost expected = blogPostDtoConverter.dtoToBlogPost(blogPostDto);

        Assertions.assertEquals(expected.getName(), blogPost.getName());

    }

}
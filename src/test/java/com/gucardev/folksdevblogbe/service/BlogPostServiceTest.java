package com.gucardev.folksdevblogbe.service;

import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.dto.converter.BlogPostDtoConverter;
import com.gucardev.folksdevblogbe.exception.BlogPostNotFoundException;
import com.gucardev.folksdevblogbe.model.BlogPost;
import com.gucardev.folksdevblogbe.repository.BlogPostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BlogPostServiceTest {


    private BlogPostService blogPostService;
    private BlogPostRepository blogPostRepository;
    private BlogPostDtoConverter blogPostDtoConverter;


    @BeforeEach
    void setup() {
        blogPostRepository = Mockito.mock(BlogPostRepository.class);
        blogPostDtoConverter = new BlogPostDtoConverter();
        blogPostService = new BlogPostService(blogPostRepository, blogPostDtoConverter);
    }

    @Test
    public void test_CreateBlogPost() {
        BlogPostDto newBlogPost = new BlogPostDto.Builder()
                .id(1L)
                .name("Post1")
                .build();

        Mockito.doReturn(blogPostDtoConverter.dtoToBlogPost(newBlogPost))
                .when(blogPostRepository).save(any());

        BlogPostDto expected = blogPostService.createBlogPost(newBlogPost);

        Assertions.assertEquals(expected, newBlogPost);

    }

    @Test
    public void test_GetBlogPostById_whenIdExistsInDatabase() {
        Long ID = 1L;
        BlogPost blogPost = new BlogPost.Builder()
                .id(ID)
                .name("Post")
                .build();

        Mockito.when(blogPostRepository.findById(ID))
                .thenReturn(Optional.ofNullable(blogPost));

        BlogPost expected = blogPostService.getBlogPostById(ID);

        Assertions.assertEquals(blogPost, expected);
    }


    @Test
    public void test_GetBlogPostById_whenIdDoesNotExistsInDatabase() {
        Long ID = 1L;
        Throwable exception = Assertions.assertThrows(BlogPostNotFoundException.class,
                () -> blogPostService.getBlogPostById(ID));
        Assertions.assertEquals(BlogPostNotFoundException.class, exception.getClass());
    }

    @Test
    public void testUpdateBlogPost_whenBlogPostIDExist_ShouldReturnUpdatedBlogPost() {
        Long ID = 1L;

        BlogPost existing = new BlogPost.Builder()
                .id(ID)
                .name("post")
                .build();

        BlogPostDto blogPostWithNewValues = new BlogPostDto.Builder()
                .id(ID)
                .name("postWithNewValues")
                .build();

        BlogPost expected = blogPostService.updateBlogPost(existing, blogPostWithNewValues);

        Assertions.assertEquals(existing, expected);

    }


}
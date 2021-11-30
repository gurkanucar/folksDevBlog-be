package com.gucardev.folksdevblogbe.service;

import com.gucardev.folksdevblogbe.TestSupport;
import com.gucardev.folksdevblogbe.controller.dto.converter.PostDtoConverter;
import com.gucardev.folksdevblogbe.exception.PostNotFoundException;
import com.gucardev.folksdevblogbe.model.Post;
import com.gucardev.folksdevblogbe.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;


@Slf4j
class PostServiceTest extends TestSupport {

    private PostService postService;
    private PostRepository postRepository;


    @BeforeEach
    void setup() {
        postRepository = Mockito.mock(PostRepository.class);
        postService = new PostService(postRepository);
    }

    @Test
    public void testGetAllBlogPosts() {

        postService.getAllPosts();
        Mockito.verify(postRepository, Mockito.times(1)).findAll();

    }


    @Test
    public void testCreatePosts() {

        final var expected = generateBlogPost(1L, "Post1", "details");
        Mockito.when(postRepository.save(expected))
                .thenReturn(expected);
        final var actual = postService.createPost(expected);
        Assertions.assertEquals(expected, actual);
        Mockito.verify(postRepository).save(expected);


    }

    @Test
    public void testGetBlogPostById_whenIdExistsInDatabase() {

        Long ID = 1L;
        final var expected = generateBlogPost(ID, "Post", "details");

        Mockito.when(postRepository.findById(ID))
                .thenReturn(Optional.ofNullable(expected));
        final var actual = postService.getPostById(ID);
        Assertions.assertEquals(expected, actual);
        Mockito.verify(postRepository, Mockito.times(1)).findById(ID);
    }


    @Test
    public void testGetBlogPostById_whenIdDoesNotExistsInDatabase() {
        Long ID = 1L;
        Throwable exception = Assertions.assertThrows(PostNotFoundException.class,
                () -> postService.getPostById(ID));
        Assertions.assertEquals(PostNotFoundException.class, exception.getClass());
    }

    @Test
    public void testUpdatePostByID_ShouldReturnUpdatedPost_whenIDExists() {

        Long ID = 1L;
        final var post = generateBlogPost(ID, "Post1", "details");
        final var expected = generateBlogPost(ID, "Post1", "postWithNewValues");

        Mockito.when(postRepository.findById(ID)).thenReturn(Optional.ofNullable(post));
        Mockito.when(postRepository.save(expected)).thenReturn(expected);

        final var actual = postService.updatePostById(ID, expected);

        Mockito.verify(postRepository, Mockito.times(1)).findById(ID);
        Mockito.verify(postRepository, Mockito.times(1)).save(expected);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testUpdatePost_ShouldReturnUpdatedPost() {

        Long ID = 1L;
        final var post = generateBlogPost(ID, "Post1", "details");
        final var expected = generateBlogPost(ID, "Post1", "postWithNewValues");
        final var actual = postService.updatePost(post, expected);
        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void testDeletePost_ShouldReturnDeletedPost_whenIDExists() {


        Long ID = 1L;
        final var post = new Post.Builder()
                .id(ID)
                .deleted(false)
                .build();

        final var expected = new Post.Builder()
                .id(ID)
                .deleted(true)
                .build();


        Mockito.when(postRepository.findById(ID)).thenReturn(Optional.ofNullable(post));
        Mockito.when(postRepository.save(expected)).thenReturn(expected);

        final var actual = postService.deletePostById(ID);

        Mockito.verify(postRepository, Mockito.times(1)).findById(ID);
        Mockito.verify(postRepository, Mockito.times(1)).save(expected);

        Assertions.assertTrue(actual.isDeleted());

    }


}
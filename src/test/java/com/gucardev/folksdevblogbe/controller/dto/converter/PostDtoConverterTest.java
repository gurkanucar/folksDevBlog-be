package com.gucardev.folksdevblogbe.controller.dto.converter;

import com.gucardev.folksdevblogbe.controller.dto.PostDto;
import com.gucardev.folksdevblogbe.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class PostDtoConverterTest {

    private PostDtoConverter postDtoConverter;


    @BeforeEach
    void setup() {
        postDtoConverter = new PostDtoConverter();
    }


    @Test
    public void test_convertBlogPostToDto() {
       final var blogPost = new Post.Builder()
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        final var expected = new PostDto.Builder()
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        PostDto actual = postDtoConverter.postToDto(blogPost);
        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void test_convertDtoToBlogPost() {

        PostDto postDto = new PostDto.Builder()
                .id(1L)
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        Post expected = new Post.Builder()
                .id(1L)
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        Post actual = postDtoConverter.dtoToPost(postDto);

        Assertions.assertEquals(expected, actual);

    }

}
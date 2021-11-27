package com.gucardev.folksdevblogbe.controller;

import com.gucardev.folksdevblogbe.IntegrationTestSupport;
import com.gucardev.folksdevblogbe.model.BlogPost;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class BlogPostControllerIT extends IntegrationTestSupport {

    public String baseUrl = "/api/v1/blogPost";

    @Test
    public void testGetBlogPostById_whenBlogPostIdExist_shouldReturnBlogPostDto() throws Exception {
        BlogPost blogPost = generateBlogPost(1L, "postName");
        blogPost = blogPostRepository.save(blogPost);

        this.mockMvc.perform(get(baseUrl + "/post/" + blogPost.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(blogPost.getId().intValue())));
    }

    @Test
    public void testGetBlogPostById_whenBlogPostIdExist_shouldReturnNotFound() throws Exception {

        this.mockMvc.perform(get(baseUrl + "/post/" + anyLong())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


}
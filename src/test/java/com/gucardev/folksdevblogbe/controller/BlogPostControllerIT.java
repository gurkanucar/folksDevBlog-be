package com.gucardev.folksdevblogbe.controller;

import com.gucardev.folksdevblogbe.IntegrationTestSupport;
import com.gucardev.folksdevblogbe.dto.BlogPostDto;
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
    public void testGetAllBlogPosts_whenRequest_shouldReturnListOfBlogPostDtos() throws Exception {

        this.mockMvc.perform(get(baseUrl + "/posts"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists());
    }


    @Test
    public void testCreateBlogPost_whenRequestBodyIncludesBlogPostDto_shouldReturnBlogPostDto() throws Exception {
        BlogPostDto blogPostDto = generateBlogPostDto(1L, "postName", "details", "video");

        String requestJson = mapper.writeValueAsString(blogPostDto);

        this.mockMvc.perform(post(baseUrl + "/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name", is(blogPostDto.getName())));

    }

    @Test
    public void testCreateBlogPost_whenRequestBodyIncludesMissingValuesOfBlogPostDto_shouldThrowError() throws Exception {
        BlogPostDto blogPostDto = generateBlogPostDto(1L, "postName");

        String requestJson = mapper.writeValueAsString(blogPostDto);

        this.mockMvc.perform(post(baseUrl + "/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is4xxClientError());

    }


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

    @Test
    public void testUpdateBlogPostByIdAndRequestBody_whenBlogPostExist_shouldReturnBlogPostDto() throws Exception {

        Long ID = anyLong();

        BlogPost blogPost = generateBlogPost(ID, "postName", "details", "video");
        blogPost = blogPostRepository.save(blogPost);
        ID = blogPost.getId();

        BlogPostDto blogPostDtoForUpdateRequest = generateBlogPostDto(
                ID, "postNameUpdated", "detailsUpdated", "videoUpdated");

        this.mockMvc.perform(put(baseUrl + "/post/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(blogPostDtoForUpdateRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is(blogPostDtoForUpdateRequest.getName())))
                .andExpect(jsonPath("$.details", is(blogPostDtoForUpdateRequest.getDetails())))
                .andExpect(jsonPath("$.videoUrl", is(blogPostDtoForUpdateRequest.getVideoUrl())));
    }


    @Test
    public void testUpdateBlogPostByIdAndRequestBody_whenBlogPostNotExist_shouldThrowError() throws Exception {

        Long ID = anyLong();

        BlogPostDto blogPostDtoForUpdateRequest = generateBlogPostDto(
                ID, "postNameUpdated", "detailsUpdated", "videoUpdated");

        String messageFromBlogPostNotFoundException = "Not found!";

        this.mockMvc.perform(put(baseUrl + "/post/" + ID + 12345L) // id doesn't exist
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(blogPostDtoForUpdateRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is(messageFromBlogPostNotFoundException)));
    }

    @Test
    public void testDeleteBlogPostById_whenBlogPostNotExist_shouldThrowBlogPostDto() throws Exception {

        Long ID = anyLong();
        BlogPost blogPost = generateBlogPost(ID, "postName", "details", "video");
        blogPost = blogPostRepository.save(blogPost);
        ID = blogPost.getId();

        this.mockMvc.perform(delete(baseUrl + "/post/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.deleted", is(true)));
    }

    @Test
    public void testDeleteBlogPostById_whenBlogPostNotExist_shouldThrowError() throws Exception {

        long ID = anyLong();
        String messageFromBlogPostNotFoundException = "Not found!";

        this.mockMvc.perform(delete(baseUrl + "/post/" + ID + 12345L) // id doesn't exist
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is(messageFromBlogPostNotFoundException)));
    }

}
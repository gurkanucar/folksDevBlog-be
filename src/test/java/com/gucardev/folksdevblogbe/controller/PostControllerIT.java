package com.gucardev.folksdevblogbe.controller;

import com.gucardev.folksdevblogbe.IntegrationTestSupport;
import com.gucardev.folksdevblogbe.controller.dto.PostDto;
import com.gucardev.folksdevblogbe.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class PostControllerIT extends IntegrationTestSupport {

    public String baseUrl = "/api/v1/blogPost";


    @Test
    @DisplayName(" testGetAllBlogPosts_whenRequest_shouldReturnListOfBlogPostDtos ")
    public void testGetAllBlogPosts_whenRequest_shouldReturnListOfBlogPostDtos() throws Exception {

        this.mockMvc.perform(get(baseUrl + "/posts"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists());
    }


    @Test
    public void testCreateBlogPost_whenRequestBodyIncludesBlogPostDto_shouldReturnBlogPostDto() throws Exception {
        PostDto postDto = generateBlogPostDto(1L, "postName", "details", "video");

        String requestJson = mapper.writeValueAsString(postDto);

        this.mockMvc.perform(post(baseUrl + "/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name", is(postDto.getName())));

    }

    @Test
    public void testCreateBlogPost_whenRequestBodyIncludesMissingValuesOfBlogPostDto_shouldThrowError() throws Exception {
        PostDto postDto = generateBlogPostDto(1L, "postName");

        String requestJson = mapper.writeValueAsString(postDto);

        this.mockMvc.perform(post(baseUrl + "/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().is4xxClientError());

    }


    @Test
    public void testGetBlogPostById_whenBlogPostIdExist_shouldReturnBlogPostDto() throws Exception {
        Post post = generateBlogPost(1L, "postName");
        post = postRepository.save(post);

        this.mockMvc.perform(get(baseUrl + "/post/" + post.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(post.getId().intValue())));
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

        Post post = generateBlogPost(ID, "postName", "details", "video");
        post = postRepository.save(post);
        ID = post.getId();

        PostDto postDtoForUpdateRequest = generateBlogPostDto(
                ID, "postNameUpdated", "detailsUpdated", "videoUpdated");

        this.mockMvc.perform(put(baseUrl + "/post/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(postDtoForUpdateRequest)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is(postDtoForUpdateRequest.getName())))
                .andExpect(jsonPath("$.details", is(postDtoForUpdateRequest.getDetails())))
                .andExpect(jsonPath("$.videoUrl", is(postDtoForUpdateRequest.getVideoUrl())));
    }


    @Test
    public void testUpdateBlogPostByIdAndRequestBody_whenBlogPostNotExist_shouldThrowError() throws Exception {

        Long ID = anyLong();

        PostDto postDtoForUpdateRequest = generateBlogPostDto(
                ID, "postNameUpdated", "detailsUpdated", "videoUpdated");

        String messageFromBlogPostNotFoundException = "Not found!";

        this.mockMvc.perform(put(baseUrl + "/post/" + ID + 12345L) // id doesn't exist
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(postDtoForUpdateRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is(messageFromBlogPostNotFoundException)));
    }

    @Test
    public void testDeleteBlogPostById_whenBlogPostNotExist_shouldThrowBlogPostDto() throws Exception {

        Long ID = anyLong();
//        BlogPost blogPost = generateBlogPost(ID, "postName", "details", "video");
//        blogPost = blogPostRepository.save(blogPost);
//        ID = blogPost.getId();

        /// EDITLE
       var id = this.mockMvc.perform(post(baseUrl + "/post/" + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.deleted", is(true)));

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
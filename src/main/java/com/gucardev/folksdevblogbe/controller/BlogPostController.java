package com.gucardev.folksdevblogbe.controller;

import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/blogPost")
public class BlogPostController {


    private final BlogPostService blogPostService;


    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<BlogPostDto>> getBlogPosts() {
        return new ResponseEntity<List<BlogPostDto>>
                (blogPostService.getAllBlogPosts(),
                        HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<BlogPostDto> getBlogPostById(@Valid @PathVariable Long id) {
        return new ResponseEntity<BlogPostDto>
                (blogPostService.getBlogPostDtoById(id),
                        HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<BlogPostDto> createBlogPost(@Valid @RequestBody BlogPostDto blogPostDto) {
        return new ResponseEntity<BlogPostDto>(
                blogPostService.createBlogPost(blogPostDto),
                HttpStatus.CREATED);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<BlogPostDto> updateBlogPostById(@PathVariable Long id,
                                                          @Valid @RequestBody BlogPostDto blogPostDto) {
        return new ResponseEntity<BlogPostDto>
                (blogPostService.updateBlogPostById(id, blogPostDto),
                        HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<BlogPostDto> deleteBlogPostById(@PathVariable Long id) {
        return new ResponseEntity<BlogPostDto>(
                blogPostService.deleteBlogPostById(id),
                HttpStatus.ACCEPTED
        );
    }


}

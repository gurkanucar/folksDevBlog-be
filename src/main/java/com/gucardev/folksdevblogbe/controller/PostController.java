package com.gucardev.folksdevblogbe.controller;

import com.gucardev.folksdevblogbe.controller.dto.PostDto;
import com.gucardev.folksdevblogbe.controller.dto.converter.PostDtoConverter;
import com.gucardev.folksdevblogbe.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/blogPost")
public class PostController {


    private final PostService postService;

    private final PostDtoConverter converter;


    public PostController(PostService postService, PostDtoConverter postDtoConverter) {
        this.postService = postService;
        this.converter = postDtoConverter;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getBlogPosts() {
        return new ResponseEntity<List<PostDto>>
                (converter
                        .convertBlogPostListToDto(postService.getAllPosts()),
                        HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getBlogPostById(@Valid @PathVariable Long id) {
        return new ResponseEntity<PostDto>
                (converter.postToDto(postService.getPostById(id)),
                        HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<PostDto> createBlogPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<PostDto>(
                converter.postToDto(postService
                        .createPost(converter.dtoToPost(postDto))),
                HttpStatus.CREATED);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostDto> updateBlogPostById(@PathVariable Long id,
                                                      @Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>
                (converter.postToDto(postService
                        .updatePostById(id, converter.dtoToPost(postDto))),
                        HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<PostDto> deleteBlogPostById(@PathVariable Long id) {
        return new ResponseEntity<PostDto>(
                converter.postToDto(postService.deletePostById(id)),
                HttpStatus.ACCEPTED
        );
    }


}

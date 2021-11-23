package com.gucardev.folksdevblogbe.service;


import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.dto.converter.BlogPostDtoConverter;
import com.gucardev.folksdevblogbe.exception.BlogPostNotFoundException;
import com.gucardev.folksdevblogbe.model.BlogPost;
import com.gucardev.folksdevblogbe.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostDtoConverter blogPostConverter;

    public BlogPostService(BlogPostRepository blogPostRepository, BlogPostDtoConverter blogPostConverter) {
        this.blogPostRepository = blogPostRepository;
        this.blogPostConverter = blogPostConverter;
    }

    public List<BlogPostDto> getAllBlogPosts() {
        return convertBlogPostListToDto(blogPostRepository.findAll());
    }


    public BlogPostDto getBlogPostDtoById(Long id) {
        return blogPostConverter.blogPostToDto(getBlogPostById(id));
    }

    protected BlogPost getBlogPostById(Long id) {
        return blogPostRepository.findById(id)
                //.orElse(null);
                .orElseThrow(() -> new BlogPostNotFoundException("Not found!"));
    }

    public BlogPostDto createBlogPost(BlogPostDto blogPostDto) {
        return blogPostConverter.blogPostToDto(
                blogPostRepository.save(blogPostConverter.dtoToBlogPost(blogPostDto))
        );
    }

    public BlogPostDto updateBlogPostById(Long id, BlogPostDto blogPostDto) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new BlogPostNotFoundException("Not found!"));
        return blogPostConverter.blogPostToDto(blogPostRepository
                .save(updateBlogPost(blogPost, blogPostDto)));
    }

    public BlogPostDto deleteBlogPostById(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new BlogPostNotFoundException("Not found!"));
        //soft delete
        blogPost.setDeleted(true);
        return blogPostConverter.blogPostToDto(blogPostRepository.save(blogPost));
    }

    protected BlogPost updateBlogPost(BlogPost existing, BlogPostDto blogPostDto) {
        existing.setName(blogPostDto.getName());
        existing.setDetails(blogPostDto.getDetails());
        existing.setImageUrl(blogPostDto.getImageUrl());
        existing.setVideoUrl(blogPostDto.getVideoUrl());
        return existing;
    }

    protected List<BlogPostDto> convertBlogPostListToDto(List<BlogPost> blogPosts) {
        return blogPosts
                .stream()
                .map(blogPostConverter::blogPostToDto)
                .collect(Collectors.toList());
    }


}

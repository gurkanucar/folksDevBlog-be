package com.gucardev.folksdevblogbe.controller.dto.converter;

import com.gucardev.folksdevblogbe.controller.dto.PostDto;
import com.gucardev.folksdevblogbe.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoConverter {


    public PostDto postToDto(Post post) {
        return new PostDto.Builder()
                .id(post.getId())
                .name(post.getName())
                .details(post.getDetails())
                .imageUrl(post.getImageUrl())
                .videoUrl(post.getVideoUrl())
                .created(post.getCreated())
                .deleted(post.isDeleted())
                .build();
    }


    public Post dtoToPost(PostDto postDto) {
        return new Post.Builder()
                .id(postDto.getId())
                .name(postDto.getName())
                .details(postDto.getDetails())
                .imageUrl(postDto.getImageUrl())
                .videoUrl(postDto.getVideoUrl())
                .deleted(postDto.isDeleted())
                .build();
    }

    public List<PostDto> convertBlogPostListToDto(List<Post> posts) {
        return posts
                .stream()
                .map(this::postToDto)
                .collect(Collectors.toList());
    }


}

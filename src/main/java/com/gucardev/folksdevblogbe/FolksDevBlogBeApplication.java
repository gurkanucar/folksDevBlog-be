package com.gucardev.folksdevblogbe;

import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.dto.converter.BlogPostDtoConverter;
import com.gucardev.folksdevblogbe.model.BlogPost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class FolksDevBlogBeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FolksDevBlogBeApplication.class, args);
    }

    @Autowired
    BlogPostDtoConverter blogPostDtoConverter;

    @Override
    public void run(String... args) throws Exception {
        BlogPost blogPost = new BlogPost.Builder()
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        BlogPostDto blogPostDto = new BlogPostDto.Builder()
                .name("name")
                .details("detail")
                .imageUrl("img")
                .videoUrl("video")
                .deleted(false)
                .build();

        BlogPostDto expected = blogPostDtoConverter.blogPostToDto(blogPost);

        log.info(expected.getName());
    }
}

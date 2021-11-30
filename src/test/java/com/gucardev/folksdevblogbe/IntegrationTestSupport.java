package com.gucardev.folksdevblogbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gucardev.folksdevblogbe.controller.PostController;
import com.gucardev.folksdevblogbe.controller.dto.PostDto;
import com.gucardev.folksdevblogbe.controller.dto.converter.PostDtoConverter;
import com.gucardev.folksdevblogbe.model.Post;
import com.gucardev.folksdevblogbe.repository.PostRepository;
import com.gucardev.folksdevblogbe.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.yml")
@DirtiesContext
@AutoConfigureMockMvc // context icerisindeki servletleri ayaga kaldirir
public class IntegrationTestSupport {

    @Autowired // integration testlerde kontrol edilebilmesi icin siniflarimizi mockalamadan ekledik
    public PostService postService;

    @Autowired
    public PostController postController;

    @Autowired
    public PostRepository postRepository;

    @Autowired
    public PostDtoConverter postDtoConverter;

    public final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    }


    public Post generateBlogPost(Long id, String name) {
        return new Post.Builder()
                .id(id)
                .name(name)
                .build();
    }

    public PostDto generateBlogPostDto(Long id, String name) {
        return new PostDto.Builder()
                .id(id)
                .name(name)
                .build();
    }

    public PostDto generateBlogPostDto(Long id, String name, String details, String videoUrl) {
        return new PostDto.Builder()
                .id(id)
                .name(name)
                .details(details)
                .videoUrl(videoUrl)
                .build();
    }

    public Post generateBlogPost(Long id, String name, String details, String videoUrl) {
        return new Post.Builder()
                .id(id)
                .name(name)
                .details(details)
                .videoUrl(videoUrl)
                .build();
    }




    public List<PostDto> generateBlogPostDtos(int size) {
        return IntStream.range(0, size)
                .mapToObj(x -> generateBlogPostDto((long) x, "name"+x))
                .collect(Collectors.toList());
    }

    public List<Post> generateBlogPosts(int size) {
        return IntStream.range(0, size)
                .mapToObj(x -> generateBlogPost((long) x, "name"+x))
                .collect(Collectors.toList());
    }


}

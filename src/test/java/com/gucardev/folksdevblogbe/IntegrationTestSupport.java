package com.gucardev.folksdevblogbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gucardev.folksdevblogbe.controller.BlogPostController;
import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.dto.converter.BlogPostDtoConverter;
import com.gucardev.folksdevblogbe.model.BlogPost;
import com.gucardev.folksdevblogbe.repository.BlogPostRepository;
import com.gucardev.folksdevblogbe.service.BlogPostService;
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
    public BlogPostService blogPostService;

    @Autowired
    public BlogPostController blogPostController;

    @Autowired
    public BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostDtoConverter blogPostDtoConverter;

    public final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    }


    public BlogPost generateBlogPost(Long id, String name) {
        return new BlogPost.Builder()
                .id(id)
                .name(name)
                .build();
    }

    public BlogPostDto generateBlogPostDto(Long id, String name) {
        return new BlogPostDto.Builder()
                .id(id)
                .name(name)
                .build();
    }


    public List<BlogPostDto> generateBlogPostDtos(int size) {
        return IntStream.range(0, size)
                .mapToObj(x -> generateBlogPostDto((long) x, "name"+x))
                .collect(Collectors.toList());
    }

    public List<BlogPost> generateBlogPosts(int size) {
        return IntStream.range(0, size)
                .mapToObj(x -> generateBlogPost((long) x, "name"+x))
                .collect(Collectors.toList());
    }


}

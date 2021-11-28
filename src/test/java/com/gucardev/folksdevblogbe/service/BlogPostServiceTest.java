package com.gucardev.folksdevblogbe.service;

import com.gucardev.folksdevblogbe.TestSupport;
import com.gucardev.folksdevblogbe.dto.BlogPostDto;
import com.gucardev.folksdevblogbe.dto.converter.BlogPostDtoConverter;
import com.gucardev.folksdevblogbe.exception.BlogPostNotFoundException;
import com.gucardev.folksdevblogbe.model.BlogPost;
import com.gucardev.folksdevblogbe.repository.BlogPostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BlogPostServiceTest extends TestSupport {

    private BlogPostService blogPostService;
    private BlogPostRepository blogPostRepository;
    private BlogPostDtoConverter blogPostDtoConverter;


    @BeforeEach
    void setup() {

        blogPostRepository = Mockito.mock(BlogPostRepository.class);
        //sor
        blogPostDtoConverter = Mockito.mock(BlogPostDtoConverter.class);
        blogPostService = new BlogPostService(blogPostRepository, blogPostDtoConverter);
    }

    @Test
    public void test_CreateBlogPost() {
        /* 1. Adim: Veri Hazirlama */
        BlogPostDto newBlogPostDto = generateBlogPostDto(1L,"Post1","details");

        BlogPost newBlogPostConverted = generateBlogPost(1L,"Post1","details");


        /* 2. Adim: Davranis belirleme (Mock siniflar icin) */
        Mockito.when(blogPostDtoConverter
                .blogPostToDto(newBlogPostConverted)).thenReturn(newBlogPostDto);

        Mockito.when(blogPostDtoConverter
                .dtoToBlogPost(newBlogPostDto)).thenReturn(newBlogPostConverted);

        Mockito.when(blogPostRepository.save(newBlogPostConverted))
                .thenReturn(newBlogPostConverted);


        /* 3. Adim: Test edilecek metodu calistir */
        BlogPostDto expected = blogPostService.createBlogPost(newBlogPostDto);

        /* 4. Adim: Sonucu, beklenen veri ile karsilastir. */
        Assertions.assertEquals(expected, newBlogPostDto);

        /* 5. Adim: hangi davranışlar gerçekleştirilmiş kontrol et */
        Mockito.verify(blogPostDtoConverter).blogPostToDto(newBlogPostConverted);
        Mockito.verify(blogPostDtoConverter).dtoToBlogPost(newBlogPostDto);
        Mockito.verify(blogPostRepository).save(newBlogPostConverted);



    }

    @Test
    public void test_GetBlogPostById_whenIdExistsInDatabase() {
        /* 1. Adim: Veri Hazirlama */
        Long ID = 1L;
        BlogPost blogPost = generateBlogPost(ID,"Post","details");

        /* 2. Adim: Davranis belirleme (Mock siniflar icin) */
        Mockito.when(blogPostRepository.findById(ID))
                .thenReturn(Optional.ofNullable(blogPost));

        /* 3. Adim: Test edilecek metodu calistir */
        BlogPost expected = blogPostService.getBlogPostById(ID);

        /* 4. Adim: Sonucu, beklenen veri ile karsilastir. */
        Assertions.assertEquals(blogPost, expected);

        /* 5. Adim: hangi davranışlar gerçekleştirilmiş kontrol et */
        Mockito.verify(blogPostRepository).findById(ID);
    }


    @Test
    public void test_GetBlogPostById_whenIdDoesNotExistsInDatabase() {
        Long ID = 1L;
        Throwable exception = Assertions.assertThrows(BlogPostNotFoundException.class,
                () -> blogPostService.getBlogPostById(ID));
        Assertions.assertEquals(BlogPostNotFoundException.class, exception.getClass());
    }

    @Test
    public void testUpdateBlogPost_ShouldReturnUpdatedBlogPost() {
        /* 1. Adim: Veri Hazirlama */
        Long ID = 1L;
        BlogPost existing = generateBlogPost(ID,"Post1","details");
        BlogPostDto blogPostWithNewValues = generateBlogPostDto(ID,"Post1","postWithNewValues");

        /* 2. Adim: Davranis belirleme (Mock siniflar icin) */
        // mock sinif yok

        /* 3. Adim: Test edilecek metodu calistir */
        BlogPost expected = blogPostService.updateBlogPost(existing, blogPostWithNewValues);

        /* 4. Adim: Sonucu, beklenen veri ile karsilastir. */
        Assertions.assertEquals(existing, expected);

    }

    @Test
    public void testupdateBlogPostById_whenBlogPostIDExist_ShouldReturnUpdatedBlogPost() {
        /* 1. Adim: Veri Hazirlama */
        Long ID = 1L;

        BlogPost existing = generateBlogPost(ID,"Post1","post");
        BlogPostDto blogPostWithNewValues = generateBlogPostDto(ID,"Post1","postWithNewValues");
        BlogPost existingAfterUpdate = generateBlogPost(ID,"Post1","postWithNewValues");


        /* 2. Adim: Davranis belirleme (Mock siniflar icin) */
        Mockito.when(blogPostRepository.findById(ID)).thenReturn(Optional.ofNullable(existing));
        //Burayi sor icindeki methodu zaten test ettik tekrar mocklamaya gerek var mi
        //Mockito.when(blogPostService.updateBlogPost(existing,blogPostWithNewValues)).thenReturn(existingAfterUpdate);
        Mockito.when(blogPostRepository.save(any())).thenReturn(existingAfterUpdate);
        Mockito.when(blogPostDtoConverter.blogPostToDto(existingAfterUpdate)).thenReturn(blogPostWithNewValues);


        /* 3. Adim: Test edilecek metodu calistir */
        BlogPostDto expected = blogPostService.updateBlogPostById(ID, blogPostWithNewValues);

        /* 4. Adim: Sonucu, beklenen veri ile karsilastir. */
        Assertions.assertEquals(expected,blogPostWithNewValues);

        /* 5. Adim: hangi davranışlar gerçekleştirilmiş kontrol et */
        Mockito.verify(blogPostRepository).findById(ID);
        //yukarda nesne verip asagida any versek olur mu?
        Mockito.verify(blogPostRepository).save(any());
        Mockito.verify(blogPostDtoConverter).blogPostToDto(any());
        //icerdeki test edilen method tekrar verify edilir mi?
        //Mockito.verify(blogPostService.updateBlogPost(existing,blogPostWithNewValues));

    }

}
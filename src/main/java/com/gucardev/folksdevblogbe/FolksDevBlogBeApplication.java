package com.gucardev.folksdevblogbe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@Slf4j
public class FolksDevBlogBeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FolksDevBlogBeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

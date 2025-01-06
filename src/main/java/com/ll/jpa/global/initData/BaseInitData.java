package com.ll.jpa.global.initData;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            Post post1 = postService.write("title1", "content1");
            System.out.println(post1.getId() + "번 글이 생성됨");
            Post post2 = postService.write("title2", "content2");
            System.out.println(post2.getId() + "번 글이 생성됨");
            Post post3 = postService.write("title3", "content3");
            System.out.println(post3.getId() + "번 글이 생성됨");
        };
    }
}

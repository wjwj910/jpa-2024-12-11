package com.ll.jpa.global.initData;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import com.ll.jpa.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Bean
    @Order(1)
    public ApplicationRunner baseInitData1ApplicationRunner() {
        return args -> {
            System.out.println("baseInitData1ApplicationRunner");
            if(postService.count() > 0) return;

            Post post1 = postService.write("title1", "content1");
            System.out.println(post1.getId() + "번 글이 생성됨");
            Post post2 = postService.write("title2", "content2");
            System.out.println(post2.getId() + "번 글이 생성됨");
            Post post3 = postService.write("title3", "content3");
            System.out.println(post3.getId() + "번 글이 생성됨");
        };
    }

    @Bean
    @Order(2)
    public ApplicationRunner baseInitData2ApplicationRunner() {
        return args -> {
            Ut.thread.sleep(1000);

            Post post1 = postService.findById(1L).get();
            postService.modify(post1, "title1-1", "content1-1");

        };
    }
}

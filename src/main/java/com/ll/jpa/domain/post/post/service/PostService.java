package com.ll.jpa.domain.post.post.service;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(String title, String content) {
        Post post = new Post();
        post.setCreatedAt(LocalDateTime.now());
        post.setModifiedAt(LocalDateTime.now());
        post.setTitle(title);
        post.setContent(content);
        post.setBlind(false);

        postRepository.save(post);

        return post;
    }
}

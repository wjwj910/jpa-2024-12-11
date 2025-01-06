package com.ll.jpa.domain.post.comment.service;

import com.ll.jpa.domain.post.comment.entity.PostComment;
import com.ll.jpa.domain.post.comment.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;

    public PostComment write(long postId, String comment) {
        PostComment postComment = PostComment.builder()
                .postId(postId)
                .content(comment)
                .build();

        return postCommentRepository.save(postComment);
    }
}

package com.ll.jpa.domain.post.post.entity;

import com.ll.jpa.domain.post.comment.entity.PostComment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {
    // long => null X
    // Long => null O
    // JPA 엔티티 클래스 특성상 id 필드는 null 이 가능하도록
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = IDENTITY) // AUTO_INCREMENT
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedAt;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean blind;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<PostComment> comments = new ArrayList<>();

    public void addComment(String comment) {
        PostComment postComment = PostComment
                .builder()
                .post(this)
                .content(comment)
                .build();

        comments.add(postComment);
    }
}

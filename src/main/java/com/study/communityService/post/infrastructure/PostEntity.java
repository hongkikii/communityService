package com.study.communityService.post.infrastructure;

import com.study.communityService.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "posts")
@Entity
@Getter
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String header;

    private String content;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    private int views;

    private int likes;

    public static PostEntity from(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.id = post.getId();
        postEntity.header = post.getHeader();
        postEntity.content = post.getContent();
        postEntity.createTime = post.getCreateTime();
        postEntity.views = post.getViews();
        postEntity.likes = post.getLikes();
        return postEntity;
    }

    public Post toModel() {
        return Post.builder()
                .id(id)
                .header(header)
                .content(content)
                .createTime(createTime)
                .views(views)
                .likes(likes)
                .build();
    }
}

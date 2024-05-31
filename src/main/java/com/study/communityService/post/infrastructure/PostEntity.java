package com.study.communityService.post.infrastructure;

import com.study.communityService.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;

    private String content;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "modification_time")
    private LocalDateTime modificationTime;

    private Integer views;

    private Integer likes;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public static PostEntity from(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.id = post.getId();
        postEntity.header = post.getHeader();
        postEntity.content = post.getContent();
        postEntity.createTime = post.getCreateTime();
        postEntity.modificationTime = post.getModificationTime();
        postEntity.views = post.getViews();
        postEntity.likes = post.getLikes();
        postEntity.isDeleted = post.getIsDeleted();
        return postEntity;
    }

    public Post toModel() {
        return Post.builder()
                .id(id)
                .header(header)
                .content(content)
                .createTime(createTime)
                .modificationTime(modificationTime)
                .views(views)
                .likes(likes)
                .isDeleted(isDeleted)
                .build();
    }
}

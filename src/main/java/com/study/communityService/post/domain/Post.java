package com.study.communityService.post.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Post {
    private Long id;
    private String header;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime modificationTime;
    private Integer views;
    private Integer likes;
    private Boolean isDeleted;

    public static Post from(PostCreate postCreate) {
        return Post.builder()
                .header(postCreate.getHeader())
                .content(postCreate.getContent())
                .createTime(LocalDateTime.now())
                .modificationTime(LocalDateTime.now())
                .views(0)
                .likes(0)
                .isDeleted(false)
                .build();
    }

    public Post update(Headerupdate headerupdate) {
        return Post.builder()
                .id(id)
                .header(headerupdate.getHeader())
                .content(content)
                .createTime(createTime)
                .modificationTime(LocalDateTime.now())
                .views(views)
                .likes(likes)
                .isDeleted(false)
                .build();
    }

    public Post update(ContentUpdate contentUpdate) {
        return Post.builder()
                .id(id)
                .header(header)
                .content(contentUpdate.getContent())
                .createTime(createTime)
                .modificationTime(LocalDateTime.now())
                .views(views)
                .likes(likes)
                .isDeleted(false)
                .build();
    }

    public Post delete() {
        return Post.builder()
                .id(id)
                .header(header)
                .content(content)
                .createTime(createTime)
                .modificationTime(modificationTime)
                .views(views)
                .likes(likes)
                .isDeleted(true)
                .build();
    }

}

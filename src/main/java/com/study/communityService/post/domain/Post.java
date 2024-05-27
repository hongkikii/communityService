package com.study.communityService.post.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {
    private Long id;
    private String header;
    private String content;
    private LocalDateTime createTime;
    private Integer views;
    private Integer likes;

    @Builder
    public Post(long id, String header, String content, LocalDateTime createTime, int views, int likes) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.createTime = createTime;
        this.views = views;
        this.likes = likes;
    }

    public static Post from(PostCreate postCreate) {
        return Post.builder()
                .header(postCreate.getHeader())
                .content(postCreate.getContent())
                .createTime(LocalDateTime.now())
                .views(0)
                .likes(0)
                .build();
    }

    public Post update(Headerupdate headerupdate) {
        return Post.builder()
                .id(id)
                .header(headerupdate.getHeader())
                .content(content)
                .createTime(createTime)
                .views(views)
                .likes(likes)
                .build();
    }

    public Post update(ContentUpdate contentUpdate) {
        return Post.builder()
                .id(id)
                .header(header)
                .content(contentUpdate.getContent())
                .createTime(createTime)
                .views(views)
                .likes(likes)
                .build();
    }
}

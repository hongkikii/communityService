package com.study.communityService.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {
    private long id;
    private String header;
    private String content;
    private LocalDateTime createTime;
    private int views;
    private int likes;

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
                .build();
    }

    public static Post modify(Headermodify headermodify) {
        return Post.builder()
                .id(headermodify.getId())
                .header(headermodify.getHeader())
                .build();
    }
}

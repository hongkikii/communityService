package com.study.communityService.post.controller.response;

import com.study.communityService.post.domain.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {
    private long id;
    private String header;
    private String content;
    private LocalDateTime createTime;
    private int views;
    private int likes;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .header(post.getHeader())
                .content(post.getContent())
                .createTime(post.getCreateTime())
                .views(post.getViews())
                .likes(post.getLikes())
                .build();

    }
}

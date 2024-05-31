package com.study.communityService.post.infrastructure;

import com.study.communityService.post.domain.Post;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "posts")
@Getter
public class PostDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String header;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createTime;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime modificationTime;

    @Field(type = FieldType.Integer)
    private Integer views;

    @Field(type = FieldType.Integer)
    private Integer likes;

    public static PostDocument from(Post post) {
        PostDocument postDocument = new PostDocument();
        postDocument.id = post.getId();
        postDocument.header = post.getHeader();
        postDocument.content = post.getContent();
        postDocument.createTime = post.getCreateTime();
        postDocument.modificationTime = post.getModificationTime();
        postDocument.views = post.getViews();
        postDocument.likes = post.getLikes();
        return postDocument;
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
                .build();
    }
}

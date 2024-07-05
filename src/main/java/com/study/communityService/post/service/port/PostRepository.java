package com.study.communityService.post.service.port;

import com.study.communityService.post.domain.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);

    List<Post> getByLatest(int startPage);

    List<Post> getByViews(int startPage);

    List<Post> getByLikes(int startPage);

    Optional<Post> findById(long id);

    List<Post> getByKeywords(int startPage, String keyword);

    void deleteById(long id);
}

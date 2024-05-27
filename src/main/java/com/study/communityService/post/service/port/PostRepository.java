package com.study.communityService.post.service.port;

import com.study.communityService.post.domain.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);

    List<Post> findByLatest(int startPage);

    List<Post> findByViews(int startPage);

    List<Post> findByLikes(int startPage);

    Optional<Post> findById(long id);

    List<Post> findByKeywords(int startPage, String keyword);

    void deleteById(long id);
}

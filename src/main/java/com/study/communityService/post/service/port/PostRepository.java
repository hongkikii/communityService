package com.study.communityService.post.service.port;

import com.study.communityService.post.domain.Post;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(long id);
}

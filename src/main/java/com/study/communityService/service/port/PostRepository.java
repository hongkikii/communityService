package com.study.communityService.service.port;

import com.study.communityService.domain.Post;

public interface PostRepository {
    Post save(Post post);
    Post findById(long id);
}

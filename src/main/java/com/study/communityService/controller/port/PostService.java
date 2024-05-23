package com.study.communityService.controller.port;

import com.study.communityService.domain.Post;
import com.study.communityService.domain.PostCreate;

public interface PostService {

    Post create(PostCreate postCreate);
}

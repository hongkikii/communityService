package com.study.communityService.controller.port;

import com.study.communityService.domain.Headermodify;
import com.study.communityService.domain.Post;
import com.study.communityService.domain.PostCreate;

public interface PostService {

    Post create(PostCreate postCreate);

    Post modify(Headermodify headermodify);
}

package com.study.communityService.post.controller.port;

import com.study.communityService.post.domain.ContentUpdate;
import com.study.communityService.post.domain.Headerupdate;
import com.study.communityService.post.domain.Post;
import com.study.communityService.post.domain.PostCreate;

public interface PostService {

    Post getById(long id);

    Post create(PostCreate postCreate);

    Post update(long id, Headerupdate headerupdate);

    Post update(long id, ContentUpdate contentUpdate);

    void deleteById(long id);
}

package com.study.communityService.service;

import com.study.communityService.controller.port.PostService;
import com.study.communityService.domain.Headermodify;
import com.study.communityService.domain.Post;
import com.study.communityService.domain.PostCreate;
import com.study.communityService.service.port.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post create(PostCreate postCreate) {
        return postRepository.save(Post.from(postCreate));
    }

    @Override
    public Post modify(Headermodify headermodify) {
        return postRepository.save(Post.modify(headermodify));
    }
}

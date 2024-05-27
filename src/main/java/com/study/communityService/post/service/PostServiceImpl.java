package com.study.communityService.post.service;

import com.study.communityService.common.domain.exception.ResourceNotFoundException;
import com.study.communityService.post.controller.port.PostService;
import com.study.communityService.post.domain.ContentUpdate;
import com.study.communityService.post.domain.Headerupdate;
import com.study.communityService.post.domain.Post;
import com.study.communityService.post.domain.PostCreate;
import com.study.communityService.post.service.port.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getLatest(int startPage) {
        return postRepository.findByLatest(startPage);
    }

    @Override
    public List<Post> getByViews(int startPage) {
        return postRepository.findByViews(startPage);
    }

    @Override
    public List<Post> getByLikes(int startPage) {
        return postRepository.findByLikes(startPage);
    }

    @Override
    public List<Post> getByKeywords(int startPage, String keyword) {
        return postRepository.findByKeywords(startPage, keyword);
    }

    @Override
    public Post getById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", id));
    }

    @Override
    public Post create(PostCreate postCreate) {
        return postRepository.save(Post.from(postCreate));
    }

    @Override
    public Post update(long id, Headerupdate headerupdate) {
        Post post = getById(id);
        return postRepository.save(post.update(headerupdate));
    }

    @Override
    public Post update(long id, ContentUpdate contentUpdate) {
        Post post = getById(id);
        return postRepository.save(post.update(contentUpdate));
    }

    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);
    }
}

package com.study.communityService.infrastructure;

import com.study.communityService.domain.Post;
import com.study.communityService.service.port.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post save(Post post) {
        return postJpaRepository.save(PostEntity.from(post)).toModel();
    }

    @Override
    public Post findById(long id) {
        return postJpaRepository.findById(id).get().toModel();
    }
}

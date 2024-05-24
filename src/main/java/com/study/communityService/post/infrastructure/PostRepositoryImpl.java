package com.study.communityService.post.infrastructure;

import com.study.communityService.post.domain.Post;
import com.study.communityService.post.service.port.PostRepository;
import java.util.Optional;
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
    public Optional<Post> findById(long id) {
        return postJpaRepository.findById(id).map(PostEntity::toModel);
    }

    @Override
    public void deleteById(long id) {
        postJpaRepository.deleteById(id);
    }
}

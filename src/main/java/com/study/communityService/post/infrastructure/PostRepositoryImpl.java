package com.study.communityService.post.infrastructure;

import com.study.communityService.common.exception.ResourceNotFoundException;
import com.study.communityService.post.domain.Post;
import com.study.communityService.post.service.port.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;
    private final PostElasticSearchRepository postElasticSearchRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        Post savedPost = postJpaRepository.save(PostEntity.from(post)).toModel();
        postElasticSearchRepository.save(PostDocument.from(savedPost));
        return savedPost;
    }

    @Override
    public List<Post> getByLatest(int startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, 10, Sort.by(Direction.DESC,"createTime"));
        return postJpaRepository.findByIsDeletedFalse(pageRequest).stream()
                .map(PostEntity::toModel)
                .toList();
    }

    @Override
    public List<Post> getByViews(int startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, 10, Sort.by(Direction.DESC,"views"));
        return postJpaRepository.findByIsDeletedFalse(pageRequest).stream()
                .map(PostEntity::toModel)
                .toList();
    }

    @Override
    public List<Post> getByLikes(int startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, 10, Sort.by(Direction.DESC,"likes"));
        return postJpaRepository.findByIsDeletedFalse(pageRequest).stream()
                .map(PostEntity::toModel)
                .toList();
    }

    @Override
    public Optional<Post> findById(long id) {
        return postJpaRepository.findById(id)
                .map(PostEntity::toModel);
    }

    @Override
    public List<Post> getByKeywords(int startPage, String keyword) {
        return postElasticSearchRepository.findByHeaderOrContent(keyword)
                .stream()
                .map(PostDocument::toModel)
                .toList();
    }

    @Override
    public void deleteById(long id) {
        Post deletePost = findById(id).map(Post::delete)
                .orElseThrow(() -> new ResourceNotFoundException("Posts", id));
        postJpaRepository.save(PostEntity.from(deletePost));
    }
}

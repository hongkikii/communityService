package com.study.communityService.post.service.port;

import com.study.communityService.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface PostRepository {
    Post save(Post post);

    List<Post> findBy(Pageable pageable);

    Optional<Post> findById(long id);

    void deleteById(long id);
}

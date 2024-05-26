package com.study.communityService.post.infrastructure;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.study.communityService.post.domain.Post;
import com.study.communityService.post.service.port.PostRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public Post save(Post post) {
        return postJpaRepository.save(PostEntity.from(post))
                .toModel();
    }

    @Override
    public List<Post> findByLatest(int startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, 10, Sort.by(Direction.DESC,"createTime"));
        return postJpaRepository.findBy(pageRequest).stream()
                .map(PostEntity::toModel)
                .toList();
    }

    @Override
    public List<Post> findByViews(int startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, 10, Sort.by(Direction.DESC,"views"));
        return postJpaRepository.findBy(pageRequest).stream()
                .map(PostEntity::toModel)
                .toList();
    }

    @Override
    public List<Post> findByLikes(int startPage) {
        PageRequest pageRequest = PageRequest.of(startPage, 10, Sort.by(Direction.DESC,"likes"));
        return postJpaRepository.findBy(pageRequest).stream()
                .map(PostEntity::toModel)
                .toList();
    }

    @Override
    public Optional<Post> findById(long id) {
        return postJpaRepository.findById(id)
                .map(PostEntity::toModel);
    }

    @Override
    public List<Post> findByKeywords(int startPage, String[] keywords) {
        String keywordQuery = String.join(" ", keywords);

        MultiMatchQuery multiMatchQuery = MultiMatchQuery.of(mq -> mq
                .query(keywordQuery)
                .fields("header^2", "content")
                .type(TextQueryType.BestFields)
        );

        SearchRequest searchRequest = new SearchRequest.Builder()
                .index("posts")
                .query(q -> q.multiMatch(multiMatchQuery))
                .from(startPage * 10)
                .size(10)
                .build();

        SearchResponse<Post> searchResponse = null;
        try {
            searchResponse = elasticsearchClient.search(searchRequest, Post.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (searchResponse != null) {
            return searchResponse.hits().hits().stream()
                    .map(hit -> hit.source())
                    .toList();
        } else {
            return List.of();
        }
    }

    @Override
    public void deleteById(long id) {
        postJpaRepository.deleteById(id);
    }
}

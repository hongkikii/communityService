package com.study.communityService.post.infrastructure;

import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostElasticSearchRepository extends ElasticsearchRepository<PostDocument, Long> {
    @Query("{\"bool\": {\"should\": [{\"match\": {\"header\": \"?0\"}}, {\"match\": {\"content\": \"?0\"}}]}}")
    List<PostDocument> findByHeaderOrContent(String keyword);
}

package com.study.communityService.post.infrastructure;

import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostElasticSearchRepository extends ElasticsearchRepository<PostDocument, Long> {
    @Query("{\"bool\": {\"should\": [{\"match\": {\"header\": {\"query\": \"?0\", \"boost\": 2}}}, {\"match\": {\"content\": {\"query\": \"?0\"}}}]}}")
    List<PostDocument> findByHeaderOrContent(String keyword);
}

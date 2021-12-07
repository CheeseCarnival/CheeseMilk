package cn.qisee.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import cn.qisee.search.entity.Blog;

@Repository
public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
}

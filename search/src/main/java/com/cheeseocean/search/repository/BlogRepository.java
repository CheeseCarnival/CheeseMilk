package com.cheeseocean.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.cheeseocean.search.entity.Blog;

@Repository
public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
}

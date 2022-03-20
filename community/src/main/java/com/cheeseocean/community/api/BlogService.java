package com.cheeseocean.community.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.cheeseocean.community.api.model.BlogDetails;

public interface BlogService {

    Slice<BlogDetails> listBlog(Pageable pageable);

    Slice<BlogDetails> listBlogByCategory(Long categoryId, Pageable pageable);

    Slice<BlogDetails> listBlogByUser(Long userId, Pageable pageable);

    BlogDetails getBlogDetails(Long blogId);

}

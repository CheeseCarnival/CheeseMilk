package com.cheeseocean.community.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cheeseocean.community.api.model.CategoryDetails;

public interface CategoryService {

    Page<CategoryDetails> listCategories(Pageable pageable);

    CategoryDetails getCategoryByName();

    CategoryDetails getCategoryById();
}

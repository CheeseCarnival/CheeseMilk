package com.cheeseocean.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cheeseocean.community.api.CategoryService;
import com.cheeseocean.community.api.model.CategoryDetails;
import com.cheeseocean.community.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Page<CategoryDetails> listCategories(Pageable pageable) {
        return null;
    }

    @Override
    public CategoryDetails getCategoryByName() {
        return null;
    }

    @Override
    public CategoryDetails getCategoryById() {
        return null;
    }
}

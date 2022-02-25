package com.cheeseocean.niot.service;

import com.cheeseocean.common.entity.Category;
import com.cheeseocean.niot.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> listCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

}

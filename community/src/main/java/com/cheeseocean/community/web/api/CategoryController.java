package com.cheeseocean.community.web.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    public static final String DEFAULT_AFTER_DATE_TIME = "1970-01-01T00:00:00.000";

//    @GetMapping
//    public Page<Category> getCategories(@PageableDefault Pageable pageable){
//        return categoryService.listCategories(pageable);
//    }

}

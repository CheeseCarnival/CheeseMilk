package com.cheeseocean.community.web.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheeseocean.common.web.response.Result;

@RestController
@RequestMapping("/blog")
public class BlogController {


    @RequestMapping("/list")
    public Result<?> listBlogs(@PageableDefault Pageable pageable){
        return null;
    }
}

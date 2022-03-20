package com.cheeseocean.community.web.response;

import com.cheeseocean.community.api.model.BlogDetails;
import com.cheeseocean.core.api.model.UserDetails;

public class BlogVO {
    UserDetails userInfo;
    BlogDetails blogInfo;

    private BlogVO(Builder builder) {
        setUserInfo(builder.userInfo);
        setBlogInfo(builder.blogInfo);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public UserDetails getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDetails userInfo) {
        this.userInfo = userInfo;
    }

    public BlogDetails getBlogInfo() {
        return blogInfo;
    }

    public void setBlogInfo(BlogDetails blogInfo) {
        this.blogInfo = blogInfo;
    }


    public static final class Builder {
        private UserDetails userInfo;
        private BlogDetails blogInfo;

        private Builder() {}

        public Builder userInfo(UserDetails val) {
            userInfo = val;
            return this;
        }

        public Builder blogInfo(BlogDetails val) {
            blogInfo = val;
            return this;
        }

        public BlogVO build() {
            return new BlogVO(this);
        }
    }
}

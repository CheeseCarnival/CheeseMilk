package com.cheeseocean.community.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.cheeseocean.community.entity.Blog;

public class BlogDetails {

    private Long uid;

    private String category;

    private String tags;

    private String content;

    private String contentPreview;

    private Long starCount;

    private Long commentCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private BlogDetails(Builder builder) {
        setUid(builder.uid);
        setCategory(builder.category);
        setTags(builder.tags);
        setContent(builder.content);
        setContentPreview(builder.contentPreview);
        setStarCount(builder.starCount);
        setCommentCount(builder.commentCount);
        setCreatedAt(builder.createdAt);
        setUpdatedAt(builder.updatedAt);
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentPreview() {
        return contentPreview;
    }

    public void setContentPreview(String contentPreview) {
        this.contentPreview = contentPreview;
    }

    public Long getStarCount() {
        return starCount;
    }

    public void setStarCount(Long starCount) {
        this.starCount = starCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public static BlogDetails from(Blog blog) {
        return new BlogDetails.Builder()
                .uid(blog.getUid())
                .category(blog.getCategory().getCategoryName())
                .tags(blog.getTags())
                .content(blog.getContent())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
    }

    public static final class Builder {
        private Long uid;
        private String category;
        private String tags;
        private String content;
        private String contentPreview;
        private Long starCount;
        private Long commentCount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder() {}

        public Builder uid(Long val) {
            uid = val;
            return this;
        }

        public Builder category(String val) {
            category = val;
            return this;
        }

        public Builder tags(String val) {
            tags = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder contentPreview(String val) {
            contentPreview = val;
            return this;
        }

        public Builder starCount(Long val) {
            starCount = val;
            return this;
        }

        public Builder commentCount(Long val) {
            commentCount = val;
            return this;
        }

        public Builder createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder updatedAt(LocalDateTime val) {
            updatedAt = val;
            return this;
        }

        public BlogDetails build() {
            return new BlogDetails(this);
        }
    }
}

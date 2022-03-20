package com.cheeseocean.core.api.model;


import com.cheeseocean.core.entity.UserInfo;

public class UserDetails {
    private Long uid;

    private String username;

    private String nickname;

    private String avatarUrl;

    private String avatarUrlPreview;

    private String location;

    private String bio;

    private Integer gender;

    private String email;

    private String status;

    private UserDetails(Builder builder) {
        setUid(builder.uid);
        setUsername(builder.username);
        setNickname(builder.nickname);
        setAvatarUrl(builder.avatarUrl);
        setAvatarUrlPreview(builder.avatarUrlPreview);
        setLocation(builder.location);
        setBio(builder.bio);
        setGender(builder.gender);
        setEmail(builder.email);
        setStatus(builder.status);
    }

    public static UserDetails from(UserInfo userInfo){
        return UserDetails.newBuilder()
                .uid(userInfo.getId())
                .username(userInfo.getUsername())
                .nickname(userInfo.getNickname())
                .avatarUrl(userInfo.getAvatarUrl())
                .location(userInfo.getLocation())
                .bio(userInfo.getBio())
                .gender(userInfo.getGender())
                .email(userInfo.getEmail())
                .status(userInfo.getStatus().name())
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrlPreview() {
        return avatarUrlPreview;
    }

    public void setAvatarUrlPreview(String avatarUrlPreview) {
        this.avatarUrlPreview = avatarUrlPreview;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static final class Builder {
        private Long uid;
        private String username;
        private String nickname;
        private String avatarUrl;
        private String avatarUrlPreview;
        private String location;
        private String bio;
        private Integer gender;
        private String email;
        private String status;

        private Builder() {}

        public Builder uid(Long val) {
            uid = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder nickname(String val) {
            nickname = val;
            return this;
        }

        public Builder avatarUrl(String val) {
            avatarUrl = val;
            return this;
        }

        public Builder avatarUrlPreview(String val) {
            avatarUrlPreview = val;
            return this;
        }

        public Builder location(String val) {
            location = val;
            return this;
        }

        public Builder bio(String val) {
            bio = val;
            return this;
        }

        public Builder gender(Integer val) {
            gender = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public UserDetails build() {
            return new UserDetails(this);
        }
    }
}

package com.cheeseocean.gateway.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("tbl_user_auth")
public class UserAuth {

    @Id
    private Long id;

    private Long userId;

    private String identifier;

    private String credential;

    private String identifyType;

    public UserAuth(Long id, String identifier, String credential) {
        this.id = id;
        this.identifier = identifier;
        this.credential = credential;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }
}

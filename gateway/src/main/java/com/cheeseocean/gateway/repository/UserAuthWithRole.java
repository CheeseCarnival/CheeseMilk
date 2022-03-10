package com.cheeseocean.gateway.repository;

public class UserAuthWithRole {

    private String identifier;

    private String credential;

    private String roleName;

    public UserAuthWithRole(String identifier, String credential, String roleName) {
        this.identifier = identifier;
        this.credential = credential;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

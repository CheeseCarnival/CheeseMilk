package com.cheeseocean.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "tbl_user")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserInfo extends BasicEntity implements Serializable {

    private static final long serialVersionUID = 749792921653839187L;

    private String username;

    private String nickname;

    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;

    private String email;

    @Column(name = "avatar_url")
    private String avatarUrl;

    //eg:0793-江西上饶、0731-湖南长沙
    private String location = null;

    private String bio;

    private LocalDateTime birth;

    //0-male, 1-female
    private Integer gender;

    @OneToMany
    @JoinTable(name = "tbl_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            })
    private Set<Role> roles;

    public enum UserStatus {
        ENABLE, OFFLINE, ONLINE, DISABLED, ACCOUNT_LOCKED
    }
}

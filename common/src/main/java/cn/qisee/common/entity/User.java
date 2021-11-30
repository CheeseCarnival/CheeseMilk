package cn.qisee.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
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
public class User extends BasicEntity implements Serializable {

    private static final long serialVersionUID = 749792921653839187L;

    //eg:studentId, teacherId, staffId...
    @Column(name = "cheese_id", unique = true)
    private String username;

    private String nickname;

    @Enumerated(EnumType.ORDINAL)
    private UserType type;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;

    @Column(name = "email", unique = true)
    private String email;

    private String avatarUrl;

    //eg:0793-江西上饶、0731-湖南长沙
    private String location = null;

    private String bio;

    private LocalDateTime birth;

    //0-male, 1-female
    private Integer gender;

    public enum UserType {
        STUDENT, TEACHER, STAFF
    }

    public enum UserStatus {
        ENABLE, OFFLINE, ONLINE, DISABLED, ACCOUNT_LOCKED
    }
}

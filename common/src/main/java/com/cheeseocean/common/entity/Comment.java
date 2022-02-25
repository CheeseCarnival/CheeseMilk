package com.cheeseocean.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Comment")
@Table(name = "tbl_comment")
@DynamicUpdate
@DynamicInsert
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BasicEntity{

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    private String content;

    private int starCount = 0;

    @Column(name = "sub_comment_count")
    private int subCommentCount = 0;
}
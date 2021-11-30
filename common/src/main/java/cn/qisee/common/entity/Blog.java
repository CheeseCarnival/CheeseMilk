package cn.qisee.common.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_blog")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@NamedEntityGraph(
        name = "Post.details",
        attributeNodes = {
                @NamedAttributeNode("images"),
        }
)
public class Blog extends BasicEntity {

    private String content;

    /**
     * 标签，使用逗号分开
     */
    private String tags;

    @ManyToOne
    private Category category;

    private Integer starCount = 0;

    private Integer commentCount = 0;

    private BlogPrivacy privacy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_post_comment")
    private Set<Comment> comments = new HashSet<>();

    public enum BlogPrivacy {
        PUBLIC, ONLY_ME, ONLY_FRIEND, ONLY_FOLLOWER
    }
}
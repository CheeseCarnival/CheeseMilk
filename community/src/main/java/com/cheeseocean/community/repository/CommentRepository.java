package com.cheeseocean.community.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cheeseocean.community.entity.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    Page<Comment> getAllByUser(User user, Pageable pageable);
//
//    Page<Comment> getAllByBlogAndCreatedAtBetweenAndParentIsNull(Blog post, LocalDateTime start, LocalDateTime end, Pageable pageable);
//
//    Optional<Comment> findCommentById(Long id);
//
//    Page<Comment> getAllByParentAndCreatedAtBetween(Comment parent, LocalDateTime start, LocalDateTime end, Pageable pageable);

//    @Modifying
//    @Query("update Comment c set c.subCommentCount = c.subCommentCount + :increment where c.id = :commentId")
//    void updateSubCommentCount(@Param("commentId") Long commentId, @Param("increment") int increment);
//
//    //返回一个帖子下的所有评论
//    Page<Comment> getCommentsById(Long postId, Pageable pageable);
//
//    @Modifying
//    @Query("update Comment c set c.starCount = c.starCount + :increment where c.id = :cid")
//    void updateStars(@Param("cid") Long cid, @Param("increment") int increment);
}

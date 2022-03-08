package com.cheeseocean.common.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.cheeseocean.common.entity.Course;
import com.cheeseocean.common.entity.UserInfo;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByUser(UserInfo userInfo);

    @Modifying
    void deleteAllByUser(UserInfo userInfo);

}

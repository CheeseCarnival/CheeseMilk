package cn.qisee.common.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import cn.qisee.common.entity.Course;
import cn.qisee.common.entity.User;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByUser(User user);

    @Modifying
    void deleteAllByUser(User user);

}

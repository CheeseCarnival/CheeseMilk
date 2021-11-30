package cn.qisee.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import cn.qisee.common.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}

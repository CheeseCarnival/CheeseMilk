package cn.qisee.search.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.qisee.search.entity.Blog;

@SpringJUnitConfig(classes = RepositoryTestConfiguration.class, initializers = ConfigDataApplicationContextInitializer.class)
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void test() {
        Blog blog = new Blog();
        blog.setCreatedAt("hello world");
        blogRepository.save(blog);
    }
}

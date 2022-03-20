package com.cheeseocean.search.repository;

import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = RepositoryTestConfiguration.class, initializers = ConfigDataApplicationContextInitializer.class)
public class BlogRepositoryTest {

}

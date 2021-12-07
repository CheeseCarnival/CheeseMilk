package cn.qisee.search.repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mapping.model.FieldNamingStrategy;
import org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy;

@EnableElasticsearchRepositories
@ComponentScan(basePackages = "cn.qisee.search.repository")
@EnableAutoConfiguration
@ImportAutoConfiguration(classes = ElasticsearchRestClientAutoConfiguration.class)
public class RepositoryTestConfiguration extends ElasticsearchConfigurationSupport {

    @Override
    protected FieldNamingStrategy fieldNamingStrategy() {
        return new SnakeCaseFieldNamingStrategy();
    }
}

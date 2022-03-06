package com.cheeseocean.gateway.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.util.ParsingUtils;

@Configuration
public class R2dbcConfig {

    @Bean
    public NamingStrategy namingStrategy() {
        return new NamingStrategy() {
            @Override
            public String getTableName(Class<?> type) {
                return ParsingUtils.reconcatenateCamelCase("tbl" + type.getSimpleName(), "_");
            }
        };
    }


}

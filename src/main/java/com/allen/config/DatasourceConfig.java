package com.allen.config;

import com.allen.component.MyJdbcTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author : allengent@163.com
 * @date : 2019/8/29 22:51
 * description :
 */

@Configuration
public class DatasourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public MyJdbcTemplate jdbcTemplate() {
        return new MyJdbcTemplate(dataSource());
    }

}

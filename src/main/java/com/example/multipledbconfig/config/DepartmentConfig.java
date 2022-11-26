package com.example.multipledbconfig.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "departmentEntityManagerFactory",  transactionManagerRef = "departmentTransactionManager",
        basePackages = "com.example.multipledbconfig.repository.department")
public class DepartmentConfig {

    @Bean(name = "departmentDataSource")
    @ConfigurationProperties(prefix = "spring.department.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "departmentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean departmentEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                           @Qualifier("departmentDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return entityManagerFactoryBuilder.
                dataSource(dataSource).
                properties(properties).
                packages("com.example.multipledbconfig.model.department").
                persistenceUnit("Department").build();
    }

    @Bean("departmentTransactionManager")
    public PlatformTransactionManager departmentTransactionManager(
            @Qualifier("departmentEntityManagerFactory") EntityManagerFactory departmentEntityManagerFactory
            ){
        return new JpaTransactionManager(departmentEntityManagerFactory);
    }
}

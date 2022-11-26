package com.example.multipledbconfig.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory",
        basePackages = "com.example.multipledbconfig.repository.user")
public class UserConfig {

    @Primary
    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.user.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder,
                                                                           @Qualifier("userDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return entityManagerFactoryBuilder.
                dataSource(dataSource).
                properties(properties).
                packages("com.example.multipledbconfig.model.user").
                persistenceUnit("User").build();
    }

    @Primary
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory
    ){
        return new JpaTransactionManager(userEntityManagerFactory);
    }
}

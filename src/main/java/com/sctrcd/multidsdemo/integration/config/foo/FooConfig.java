package com.sctrcd.multidsdemo.integration.config.foo;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "fooEntityManagerFactory",
        transactionManagerRef = "fooTransactionManager",
        basePackages = {"com.sctrcd.multidsdemo.integration.repositories.foo"})
public class FooConfig {

    @Bean(name = "fooDataSource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("foodb")
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

    @Bean(name = "fooEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("fooDataSource") DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.sctrcd.multidsdemo.domain.foo");
        lef.setPersistenceUnitName("fooPersistenceUnit");
        return lef;
    }

    @Bean(name = "fooTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("fooEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

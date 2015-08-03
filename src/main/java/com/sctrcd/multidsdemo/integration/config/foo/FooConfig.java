package com.sctrcd.multidsdemo.integration.config.foo;

import com.sctrcd.multidsdemo.domain.foo.Foo;
import com.sctrcd.multidsdemo.integration.repositories.foo.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
import java.util.Collections;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "fooEntityManagerFactory",
        transactionManagerRef = "fooTransactionManager",
        basePackageClasses = FooRepository.class)
public class FooConfig {

    @Autowired
    private JpaProperties jpaProperties;


    @Bean(name = "fooDataSource")
    @Primary  // Pull in the JPA configuration via this data source's definition.
    @ConfigurationProperties(prefix = "spring.fooDataSource")
    public DataSource fooDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    @Bean(name = "fooEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factory,
                                                                       @Qualifier("fooDataSource") DataSource fds) {
        return factory
                .dataSource(fds)
                .packages(Foo.class)
                .persistenceUnit("fooPersistenceUnit")

                        // This is to work-around a bug in Spring boot, which is not setting the naming strategy when there
                        // are multiple data sources and entity managers.
                .properties(Collections.singletonMap("hibernate.ejb.naming_strategy",
                        jpaProperties.getHibernate().getNamingStrategy()))

                .build();
    }

    @Autowired
    @Bean(name = "fooTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("fooEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

}

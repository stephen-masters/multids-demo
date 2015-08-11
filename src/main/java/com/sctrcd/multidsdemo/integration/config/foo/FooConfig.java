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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factoryBuilder,
                                                                       @Qualifier("fooDataSource") DataSource fds) {
        return factoryBuilder
                .dataSource(fds)
                .packages(Foo.class)
                .persistenceUnit("fooPersistenceUnit")
                        // Using Hibernate and Not using JTA.  (Change the next line if your context is different.)
                .properties(this.jpaProperties.getHibernateProperties(fds))
                .build();
    }

    @Autowired
    @Bean(name = "fooTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("fooEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

}

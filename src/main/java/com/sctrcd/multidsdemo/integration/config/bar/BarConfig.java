package com.sctrcd.multidsdemo.integration.config.bar;

import com.sctrcd.multidsdemo.domain.bar.BarEntity;
import com.sctrcd.multidsdemo.integration.repositories.bar.BarEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "barEntityManagerFactory",
        transactionManagerRef = "barTransactionManager",
        basePackageClasses = BarEntityRepository.class)
public class BarConfig {

    @Autowired
    private JpaProperties jpaProperties;


    @Bean(name = "barDataSource")
    @ConfigurationProperties(prefix = "spring.barDataSource")
    public DataSource barDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    @Bean(name = "barEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factory,
                                                                       @Qualifier("barDataSource") DataSource fds) {
        return factory
                .dataSource(fds)
                .packages(BarEntity.class)
                .persistenceUnit("barPersistenceUnit")

                        // This is to work-around a bug in Spring boot, which is not setting the naming strategy when there
                        // are multiple data sources and entity managers.
                .properties(Collections.singletonMap("hibernate.ejb.naming_strategy",
                        jpaProperties.getHibernate().getNamingStrategy()))

                .build();
    }

    @Autowired
    @Bean(name = "barTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("barEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

}

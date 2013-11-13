package com.sctrcd.multidsdemo.integration.config.foo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        //entityManagerFactoryRef = "fooEmf", 
        basePackages = {"com.sctrcd.multidsdemo.integration.repositories.foo"})
public class FooConfig {

    @Autowired
    JpaVendorAdapter jpaVendorAdapter;

    /**
     * Primary because if we have activated embedded databases, we do not want
     * the application to connect to an external database.
     */
    @Bean//(name = "fooDs")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("bardb")
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

    @Bean//(name = "fooEm")
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Bean//(name = "fooEmf")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.sctrcd.multidsdemo.domain.foo");
        lef.setPersistenceUnitName("fooPersistenceUnit");
        lef.afterPropertiesSet();
        return lef.getObject();
    }

    @Bean//(name = "fooTm")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
    
}

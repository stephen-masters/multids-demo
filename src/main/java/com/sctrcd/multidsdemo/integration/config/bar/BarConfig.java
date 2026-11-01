package com.sctrcd.multidsdemo.integration.config.bar;

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
        entityManagerFactoryRef = "barEntityManagerFactory",
        transactionManagerRef = "barTransactionManager",
        basePackages = {"com.sctrcd.multidsdemo.integration.repositories.bar"})
public class BarConfig {

    @Bean(name = "barDataSource")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setName("bardb")
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

    @Bean(name = "barEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("barDataSource") DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.sctrcd.multidsdemo.domain.bar");
        lef.setPersistenceUnitName("barPersistenceUnit");
        return lef;
    }

    @Bean(name = "barTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("barEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

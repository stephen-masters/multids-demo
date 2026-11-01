package com.sctrcd.multidsdemo.integration.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Spring Boot backs off its own datasource and JPA auto-configuration as soon
 * as there is more than one {@code DataSource} and no primary, so each
 * datasource is wired explicitly in {@code FooConfig} and {@code BarConfig}.
 */
@SpringBootApplication(scanBasePackages = { "com.sctrcd.multidsdemo" })
public class AppConfig {

    /**
     * Shared by both entity manager factories.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

}

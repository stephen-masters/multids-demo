package com.sctrcd.multidsdemo.integration.repositories.foo;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sctrcd.multidsdemo.domain.foo.Foo;
import com.sctrcd.multidsdemo.integration.config.AppConfig;

@SpringBootTest(classes = AppConfig.class)
class FooRepositoryTest {

    @Autowired
    FooRepository fooRepo;

    @Autowired
    @Qualifier("fooDataSource")
    DataSource fooDataSource;

    @Autowired
    @Qualifier("barDataSource")
    DataSource barDataSource;

    @Test
    void savesFooToFooDatabaseOnly() {
        JdbcTemplate foodb = new JdbcTemplate(fooDataSource);
        Integer before = foodb.queryForObject("select count(*) from FOO", Integer.class);

        Foo saved = fooRepo.saveAndFlush(new Foo("foo"));

        assertThat(saved.getId()).isNotNull();
        assertThat(foodb.queryForObject("select count(*) from FOO", Integer.class)).isEqualTo(before + 1);
        assertThat(new JdbcTemplate(barDataSource).queryForObject(
                "select count(*) from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'FOO'", Integer.class))
                .isZero();
    }

}

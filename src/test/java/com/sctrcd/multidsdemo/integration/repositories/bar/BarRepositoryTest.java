package com.sctrcd.multidsdemo.integration.repositories.bar;

import static org.assertj.core.api.Assertions.assertThat;

import com.sctrcd.multidsdemo.domain.bar.Bar;
import com.sctrcd.multidsdemo.integration.config.AppConfig;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = AppConfig.class)
class BarRepositoryTest {

    @Autowired
    BarRepository barRepo;

    @Autowired
    @Qualifier("barDataSource")
    DataSource barDataSource;

    @Autowired
    @Qualifier("fooDataSource")
    DataSource fooDataSource;

    @Test
    void savesBarToBarDatabaseOnly() {
        JdbcTemplate bardb = new JdbcTemplate(barDataSource);
        Integer before = bardb.queryForObject("select count(*) from BAR", Integer.class);

        Bar saved = barRepo.saveAndFlush(new Bar("bar"));

        assertThat(saved.getId()).isNotNull();
        assertThat(bardb.queryForObject("select count(*) from BAR", Integer.class))
                .isEqualTo(before + 1);
        assertThat(new JdbcTemplate(fooDataSource)
                        .queryForObject(
                                "select count(*) from INFORMATION_SCHEMA.TABLES where TABLE_NAME = 'BAR'",
                                Integer.class))
                .isZero();
    }
}

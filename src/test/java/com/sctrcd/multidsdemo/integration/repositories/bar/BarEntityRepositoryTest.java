package com.sctrcd.multidsdemo.integration.repositories.bar;

import com.sctrcd.multidsdemo.domain.bar.BarEntity;
import com.sctrcd.multidsdemo.integration.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
@Slf4j
public class BarEntityRepositoryTest {

    @Autowired
    BarEntityRepository barRepo;

    @Test
    public void test() {
        try {
            BarEntity bar = new BarEntity("bar");
            barRepo.saveAndFlush(bar);
        } catch (Exception e) {
            log.error("Error saving Bar.", e);
            throw e;
        }
    }

}

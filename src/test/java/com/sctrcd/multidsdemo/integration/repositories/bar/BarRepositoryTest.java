package com.sctrcd.multidsdemo.integration.repositories.bar;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.sctrcd.multidsdemo.domain.bar.Bar;
import com.sctrcd.multidsdemo.integration.config.AppConfig;
import com.sctrcd.multidsdemo.integration.repositories.bar.BarRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class BarRepositoryTest {

    private static Logger log = LoggerFactory.getLogger(BarRepositoryTest.class);
    
    @Autowired
    BarRepository barRepo;
    
    @Test
    public void test() {
        try {
            Bar bar = new Bar("bar");
            barRepo.saveAndFlush(bar);
        } catch (Exception e) {
            log.error("Error saving Bar.", e);
            throw e;
        }
    }

}

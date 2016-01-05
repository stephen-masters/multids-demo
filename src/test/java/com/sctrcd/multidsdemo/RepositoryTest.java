package com.sctrcd.multidsdemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sctrcd.multidsdemo.bar.domain.Bar;
import com.sctrcd.multidsdemo.bar.repo.BarRepository;
import com.sctrcd.multidsdemo.foo.domain.Foo;
import com.sctrcd.multidsdemo.foo.repo.FooRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MultiDsApplication.class)
public class RepositoryTest {

    private static Logger log = LoggerFactory.getLogger(RepositoryTest.class);

    @Autowired
    private FooRepository fooRepo;

    @Autowired
    private BarRepository barRepo;

    @Test
    public void shouldSaveFoo() {
        int countBefore = fooRepo.findAll().size();
        try {
            Foo foo = new Foo();
            foo.setName("foo");
            fooRepo.saveAndFlush(foo);
        } catch (Exception e) {
            log.error("Error saving Foo.", e);
            throw e;
        }
        int countAfter = fooRepo.findAll().size();
        assertEquals(1, countAfter - countBefore);
    }

    @Test
    public void shouldSaveBar() {
        int countBefore = barRepo.findAll().size();
        try {
            Bar bar = new Bar();
            bar.setName("bar");
            barRepo.saveAndFlush(bar);
        } catch (Exception e) {
            log.error("Error saving Bar.", e);
            throw e;
        }
        int countAfter = barRepo.findAll().size();
        assertEquals(1, countAfter - countBefore);
    }

}

package com.sctrcd.multidsdemo.integration.repositories.foo;

import com.sctrcd.multidsdemo.domain.foo.Foo;
import com.sctrcd.multidsdemo.integration.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
@Slf4j
public class FooRepositoryTest {

    @Autowired
    FooRepository fooRepo;

    @Autowired
    @Qualifier("fooEntityManagerFactory")
    EntityManagerFactory femf;

    @Test
    public void test() {
        try {
            Foo foo = new Foo("foo");
            foo.setSocialSecurityNumber("123-45-6789");
            fooRepo.saveAndFlush(foo);

            SessionFactory sf = femf.unwrap(SessionFactory.class);
            AbstractEntityPersister fcm = ((AbstractEntityPersister) sf.getClassMetadata(Foo.class));
            String[] cnames = fcm.getPropertyColumnNames("socialSecurityNumber");
            assertEquals(1, cnames.length);
            assertEquals("social_security_number", cnames[0]);

        } catch (Exception e) {
            log.error("Error saving Foo.", e);
            throw e;
        }
    }

}

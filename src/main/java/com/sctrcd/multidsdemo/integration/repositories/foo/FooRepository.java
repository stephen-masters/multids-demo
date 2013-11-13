package com.sctrcd.multidsdemo.integration.repositories.foo;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sctrcd.multidsdemo.domain.foo.Foo;

@PersistenceContext(unitName = "fooPersistenceUnit")
@Transactional("fooTm")
public interface FooRepository extends JpaRepository<Foo, Long> {

}

package com.sctrcd.multidsdemo.integration.repositories.foo;

import com.sctrcd.multidsdemo.domain.foo.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FooRepository extends JpaRepository<Foo, Long> {

}

package com.sctrcd.multidsdemo.foo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sctrcd.multidsdemo.foo.domain.Foo;

public interface FooRepository extends JpaRepository<Foo, Long> {

}

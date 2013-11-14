package com.sctrcd.multidsdemo.integration.repositories.bar;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sctrcd.multidsdemo.domain.bar.Bar;

public interface BarRepository extends JpaRepository<Bar, Long> {

}

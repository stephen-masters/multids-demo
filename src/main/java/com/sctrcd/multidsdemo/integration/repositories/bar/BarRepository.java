package com.sctrcd.multidsdemo.integration.repositories.bar;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sctrcd.multidsdemo.domain.bar.Bar;

@PersistenceContext(unitName = "barPersistenceUnit")
@Transactional("barTm")
public interface BarRepository extends JpaRepository<Bar, Long> {

}

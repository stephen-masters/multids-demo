package com.sctrcd.multidsdemo.bar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sctrcd.multidsdemo.bar.domain.Bar;

public interface BarRepository extends JpaRepository<Bar, Long> {

}

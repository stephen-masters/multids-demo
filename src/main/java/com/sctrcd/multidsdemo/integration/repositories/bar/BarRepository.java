package com.sctrcd.multidsdemo.integration.repositories.bar;

import com.sctrcd.multidsdemo.domain.bar.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> {}

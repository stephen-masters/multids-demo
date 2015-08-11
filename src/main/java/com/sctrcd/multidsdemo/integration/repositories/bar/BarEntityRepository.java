package com.sctrcd.multidsdemo.integration.repositories.bar;

import com.sctrcd.multidsdemo.domain.bar.BarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarEntityRepository extends JpaRepository<BarEntity, Long> {

}

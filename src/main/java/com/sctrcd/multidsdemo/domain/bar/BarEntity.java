package com.sctrcd.multidsdemo.domain.bar;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "bar")
public class BarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    public BarEntity(String name) {
        this.name = name;
    }

}

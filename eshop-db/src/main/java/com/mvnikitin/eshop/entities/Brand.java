package com.mvnikitin.eshop.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}

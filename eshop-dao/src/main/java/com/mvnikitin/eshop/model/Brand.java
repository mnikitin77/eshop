package com.mvnikitin.eshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(generator = "brand_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "brand_id_seq",
            sequenceName = "brand_id_seq",
            allocationSize = 1)
    private Integer id;

    @Column
    private String name;
}

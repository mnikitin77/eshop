package com.mvnikitin.eshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(generator = "role_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "role_id_seq",
            sequenceName = "role_id_seq",
            allocationSize = 1)
    private Integer id;

    @Column
    private String name;
}

package com.mvnikitin.eshop.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandDTO  implements Serializable {

    private static final long serialVersionUID = -6318297377028780688L;

    private Integer id;
    private String name;
}

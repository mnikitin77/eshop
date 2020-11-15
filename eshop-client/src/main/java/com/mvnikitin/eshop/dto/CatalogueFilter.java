package com.mvnikitin.eshop.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CatalogueFilter implements Serializable {

    private static final long serialVersionUID = 4467799541446752922L;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer rows;
    private String sortBy;

    private boolean isApplied;
}

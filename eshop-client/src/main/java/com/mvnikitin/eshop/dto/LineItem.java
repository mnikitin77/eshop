package com.mvnikitin.eshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class LineItem implements Serializable {

    private static final long serialVersionUID = -3422571419637381164L;

    private ProductDTO product;
    private Integer quantity;

    public LineItem(ProductDTO product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineItem item = (LineItem) o;

        return product.equals(item.product);
    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }
}

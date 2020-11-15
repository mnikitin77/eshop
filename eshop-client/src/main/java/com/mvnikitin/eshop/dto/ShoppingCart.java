package com.mvnikitin.eshop.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 7737722188898346728L;

    private Set<LineItem> items = new HashSet<>();
    private List<LineItem> itemsForUpdate;

    public Set<LineItem> getItems() {
        return items;
    }

    public void removeItem(ProductDTO product) {
        LineItem item = new LineItem(product, 1);
        removeItem(item);
    }

    public void removeItem(LineItem item) {
        items.remove(item);
    }

    public void addOrModifyItem(ProductDTO product, Integer quantity) {
        LineItem item = new LineItem(product, quantity);
        addOrModifyItem(item, quantity);
    }

    public void addOrModifyItem(LineItem item, Integer quantity) {
        Integer previousQuantity = items.stream()
                .filter(i -> i.equals(item))
                .map(i -> i.getQuantity())
                .reduce((res, i) -> i).orElse(null);

        // Previous item has the outdated quantity value
        // (quantity is not used in equals() and hashcode() implementation).
        if (items.remove(item)) {
            item.setQuantity(previousQuantity + quantity);
        }
        items.add(item);
    }

    public void clear() {
        items.clear();
    }

    public Integer count() {
        return items.size();
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(item -> item.getTotal())
                .reduce((sum, itemTotal) -> sum.add(itemTotal)).orElse(null);
    }

    public List<LineItem> getItemsForUpdate() {
        itemsForUpdate = new ArrayList<>(items);
        itemsForUpdate.sort(
                Comparator.comparing(i -> i.getProduct().getName()));
        return itemsForUpdate;
    }

    public void update() {
        items = items.stream()
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toSet());
    }
}

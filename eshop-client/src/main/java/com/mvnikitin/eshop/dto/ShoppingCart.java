package com.mvnikitin.eshop.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 7737722188898346728L;

    private Map<LineItem, LineItem> items = new HashMap<>();
    protected List<LineItem> itemsForUpdate;

    public void removeItem(ProductDTO product) {
        LineItem item = new LineItem(product);
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
        LineItem oldItem = items.get(item);
        if (oldItem != null) {
            item.setQuantity(oldItem.getQuantity() + quantity);
        }
        items.put(item, item);
    }

    public void clear() {
        items.clear();
    }

    public Integer count() {
        return items.size();
    }

    public List<LineItem> getItems() {
        List<LineItem> lineItems = new ArrayList(items.values());
        lineItems.sort(Comparator.comparing(i -> i.getProduct().getName()));
        return lineItems;
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(item -> item.getTotal())
                .reduce((sum, itemTotal) ->
                        sum.add(itemTotal)).orElse(BigDecimal.valueOf(0));
    }

    public void update() {
        for (LineItem i: itemsForUpdate) {
            if (i.getQuantity().equals(Integer.valueOf(0))) {
                items.remove(i);
            } else {
                items.put(i, i);
            }
        }
    }

//    It's a getter for the itemsForUpdate field and
//    it is used by Thymeleaf to generate the html page.
    public List<LineItem> getItemsForUpdate() {
        itemsForUpdate = new ArrayList<>(items.values());
        itemsForUpdate.sort(
                Comparator.comparing(i -> i.getProduct().getName()));
        return itemsForUpdate;
    }
}

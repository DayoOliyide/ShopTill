package io.superstore.discounts;

import io.superstore.data.Item;

/**
 * Interface represents the discount that's applied to an Item.
 */
public interface Discount
{
    String getItemName();

    boolean isApplicable(Item item, int numberOfItems);

    long calculateDiscount(long itemPrice, int numberOfItems);
}

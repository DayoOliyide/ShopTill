package io.superstore.discounts;

import io.superstore.data.Item;

/**
 * An implementation of the Buy One Get One Free discount
 */
public class BogofDiscount implements Discount
{
    private String itemName;

    public BogofDiscount(String itemName)
    {
        this.itemName = itemName;
    }

    @Override
    public String getItemName()
    {
        return itemName;
    }

    @Override
    public boolean isApplicable(Item item, int numberOfItems)
    {
        return itemName.equals(item.getName()) && (numberOfItems > 1);
    }

    @Override
    public long calculateDiscount(long itemPrice, int numberOfItems)
    {
        long discount = (numberOfItems / 2) * itemPrice;
        return discount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BogofDiscount that = (BogofDiscount) o;

        if (!itemName.equals(that.itemName)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return itemName.hashCode();
    }
}

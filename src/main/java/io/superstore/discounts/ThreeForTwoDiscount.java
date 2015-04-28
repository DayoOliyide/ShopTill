package io.superstore.discounts;

import io.superstore.data.Item;

/**
 * An Implementation of the 3 for 2 discount
 */
public class ThreeForTwoDiscount implements Discount
{
    private String itemName;

    public ThreeForTwoDiscount(String itemName)
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
        return itemName.equals(item.getName()) && (numberOfItems > 2);
    }

    @Override
    public long calculateDiscount(long itemPrice, int numberOfItems)
    {
        long discount = (numberOfItems / 3) * itemPrice;
        return  discount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreeForTwoDiscount that = (ThreeForTwoDiscount) o;

        if (!itemName.equals(that.itemName)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return itemName.hashCode();
    }
}

package io.superstore.services;

import io.superstore.data.Basket;
import io.superstore.data.Item;
import io.superstore.discounts.Discount;

import java.util.Map;
import java.util.Set;

/**
 * A default calculator service
 */
public class DefaultCalculatorService implements CalculatorService
{
    @Override
    /**
     * Calculates the total price of a basket by going through all the items,
     * 1) Calculating the itemTotal by multiplying the item price by the number of particular item
     * 2) Adding all applicable discounts, for that particular item, together
     * 3) Get the real itemTotal by subtracting total item discount
     * 4) Doing this for all items and adding it all together
     */
    public long calculateTotal(Basket basket)
    {
        long total = 0;
        Map<Item, Integer> itemsAndAmount = basket.getAllItemsAndAmount();
        Set<Item> items = itemsAndAmount.keySet();

        for(Item item : items)
        {
            int itemAmount = itemsAndAmount.get(item);
            long itemTotal = item.getPrice() * itemAmount;

            long itemTotalDiscount = 0;
            Set<Discount> applicableItemDiscounts = basket.getApplicableDiscounts(item.getName());
            for(Discount discount : applicableItemDiscounts)
            {
                itemTotalDiscount += discount.calculateDiscount(item.getPrice(), itemAmount);
            }

            itemTotal -= itemTotalDiscount;
            total += itemTotal;
        }

        return total;
    }
}

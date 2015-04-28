package io.superstore.services;

import io.superstore.data.Basket;
import io.superstore.data.Item;
import io.superstore.discounts.Discount;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Default Implementation of the Discount Service
 */
public class DefaultDiscountService implements DiscountService
{
    Map<String, Discount> allKnownDiscounts = new HashMap<String, Discount>();

    @Override
    public void addOrUpdate(Discount discount)
    {
        allKnownDiscounts.put(discount.getItemName(), discount);
    }

    @Override
    public Discount getDiscount(String itemName)
    {
        return allKnownDiscounts.get(itemName);
    }

    @Override
    /**
     * Attaches (adds to the basket) all known discounts that match items in the basket
     */
    public Basket attachDiscounts(Basket basket)
    {
        Map<Item, Integer> allItemsAndAmount = basket.getAllItemsAndAmount();
        Set<Item> allItems = allItemsAndAmount.keySet();
        for(Item item : allItems)
        {
            Discount foundDiscount = allKnownDiscounts.get(item.getName());
            if(foundDiscount != null)
            {
                Integer itemAmount = allItemsAndAmount.get(item);
                if (foundDiscount.isApplicable(item, itemAmount))
                {
                    basket.addApplicableDiscount(foundDiscount);
                }
            }
        }
        return basket;
    }
}

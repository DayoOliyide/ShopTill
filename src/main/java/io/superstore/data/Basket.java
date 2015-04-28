package io.superstore.data;

import io.superstore.discounts.Discount;

import java.util.*;

/**
 * Represents a Basket of items and associated applicable discounts
 */
public class Basket
{
    private Map<Item, Integer> allItemsAndAmount = new HashMap<>();
    private Map<String, Set<Discount>> applicableDiscounts = new HashMap<>();

    public void addItem(Item item)
    {
        Integer numberOfItem = allItemsAndAmount.get(item);
        if(numberOfItem == null)
        {
            allItemsAndAmount.put(item, 1);
        }
        else
        {
            allItemsAndAmount.put(item, ++numberOfItem);
        }
    }

    public void addApplicableDiscount(Discount discount)
    {
        Set<Discount> discounts = applicableDiscounts.get(discount.getItemName());
        if(discounts == null)
        {
            discounts = new HashSet<>();
            applicableDiscounts.put(discount.getItemName(), discounts);
        }
        discounts.add(discount);
    }

    public Map<Item, Integer> getAllItemsAndAmount()
    {
        return Collections.unmodifiableMap(allItemsAndAmount);
    }

    public Set<Discount> getApplicableDiscounts(String itemName)
    {
        Set<Discount> foundDiscounts = applicableDiscounts.get(itemName);
        Set<Discount> applicableDiscounts;
        if(foundDiscounts == null)
        {
            applicableDiscounts = Collections.emptySet();
        }
        else
        {
            applicableDiscounts = Collections.unmodifiableSet(foundDiscounts);
        }
        return applicableDiscounts;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        if (!allItemsAndAmount.equals(basket.allItemsAndAmount)) return false;
        if (!applicableDiscounts.equals(basket.applicableDiscounts)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = allItemsAndAmount.hashCode();
        result = 31 * result + applicableDiscounts.hashCode();
        return result;
    }
}

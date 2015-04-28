package io.superstore.services;

import io.superstore.data.Basket;
import io.superstore.data.Item;
import io.superstore.discounts.Discount;

/**
 * An interface for storing and finding Discounts
 */
public interface DiscountService
{

    void addOrUpdate(Discount discount);

    Discount getDiscount(String itemName);

    Basket attachDiscounts(Basket basket);
}

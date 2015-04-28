package io.superstore.services;

import io.superstore.data.Basket;
import io.superstore.data.Item;
import io.superstore.discounts.BogofDiscount;
import io.superstore.discounts.BogofDiscountTest;
import io.superstore.discounts.Discount;
import io.superstore.discounts.ThreeForTwoDiscount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Test Class for DefaultDiscountService
 */
public class DefaultDiscountServiceTest
{
    private DefaultDiscountService discountService;

    @Before
    public void setup()
    {
        discountService = new DefaultDiscountService();
    }

    @Test
    public void testAddDiscount()
    {
        String itemName = "Apricot";
        Discount expectedDiscount = new BogofDiscount(itemName);
        discountService.addOrUpdate(expectedDiscount);
        Discount actualDiscount = discountService.getDiscount(itemName);
        Assert.assertEquals("Expected Discount is NOT returned", expectedDiscount, actualDiscount);
    }

    @Test
    public void testUpdateDiscount()
    {
        String itemNameA = "Doughnut";
        Discount originalDiscount = new BogofDiscount(itemNameA);
        Discount updatedDiscount = new ThreeForTwoDiscount(itemNameA);
        discountService.addOrUpdate(originalDiscount);
        discountService.addOrUpdate(updatedDiscount);
        Discount returnedDiscount = discountService.getDiscount(itemNameA);
        Assert.assertNotEquals("The returned discount should NOT be original", originalDiscount, returnedDiscount);
        Assert.assertEquals("The returned discount SHOULD be updated", updatedDiscount, returnedDiscount);
    }

    @Test
    public void testAttachDiscounts()
    {
        String itemNameA  = "Apple Turnover";
        String itemNameB = "Lychee";
        String itemNameC = "Pineapple";
        Item itemA = new Item(itemNameA, 23);
        Item itemB = new Item(itemNameB, 34);
        Item itemC = new Item(itemNameC, 40);
        Basket suppliedBasket = new Basket();
        suppliedBasket.addItem(itemA);
        suppliedBasket.addItem(itemB); suppliedBasket.addItem(itemB); //to match bogoff
        suppliedBasket.addItem(itemC); suppliedBasket.addItem(itemC); //to match bogoff

        Discount itemBDiscount = new BogofDiscount(itemNameB);
        Discount itemCDiscount = new BogofDiscount(itemNameC);
        Set<Discount> expectedItemBDiscounts = new HashSet<>();
        expectedItemBDiscounts.add(itemBDiscount);
        Set<Discount> expectedItemCDiscounts = new HashSet<>();
        expectedItemCDiscounts.add(itemCDiscount);
        discountService.addOrUpdate(itemBDiscount);
        discountService.addOrUpdate(itemCDiscount);
        discountService.attachDiscounts(suppliedBasket);
        Set<Discount> returnedItemBDiscounts = suppliedBasket.getApplicableDiscounts(itemNameB);
        Set<Discount> returnedItemCDiscounts = suppliedBasket.getApplicableDiscounts(itemNameC);

        Assert.assertEquals("ItemB's applicable discounts are NOT what was expected", expectedItemBDiscounts, returnedItemBDiscounts);
        Assert.assertEquals("ItemC's applicable discounts are NOT what was expected", expectedItemCDiscounts, returnedItemCDiscounts);    }

}

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

/**
 * Test class for DefaultCalculatorService
 */
public class DefaultCalculatorServiceTest
{
    private DefaultCalculatorService calculatorService;

    @Before
    public void setup()
    {
        calculatorService = new DefaultCalculatorService();
    }

    @Test
    public void testCalculateTotal()
    {
        Item apple = new Item("Apple", 35);
        Item banana = new Item("Banana", 20);
        Item melon = new Item("Melon", 50);
        Item lime = new Item("Lime", 15);
        Basket testBasket = new Basket();
        testBasket.addItem(apple);
        testBasket.addItem(banana);
        testBasket.addItem(melon); testBasket.addItem(melon); //for any discounts
        testBasket.addItem(lime); testBasket.addItem(lime); testBasket.addItem(lime); //for any discounts
        long total = 35 + 20 + (50 * 2) + (15 * 3);
        long totalWithDiscounts = 35 + 20 + 50 + (15 * 2);

        long returnedTotal = calculatorService.calculateTotal(testBasket);
        Assert.assertEquals("The calculated total is NOT what was expected", total, returnedTotal);

        Discount melonDiscount = new BogofDiscount("Melon");
        Discount limeDiscount = new ThreeForTwoDiscount("Lime");
        testBasket.addApplicableDiscount(melonDiscount);
        testBasket.addApplicableDiscount(limeDiscount);

        long returnedTotalWithDiscounts = calculatorService.calculateTotal(testBasket);
        Assert.assertEquals("The discounted total is NOT what was expected", totalWithDiscounts, returnedTotalWithDiscounts);
    }

}

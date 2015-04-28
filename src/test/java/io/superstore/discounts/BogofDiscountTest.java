package io.superstore.discounts;

import io.superstore.data.Item;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class to Test BogofDiscount
 */
public class BogofDiscountTest
{
    @Test
    public void testIsApplicable()
    {
        String itemAName = "Carrot";
        String itemBName = "Beans";
        Item itemA = new Item(itemAName, 25);
        Item itemB = new Item(itemBName, 30);
        BogofDiscount discount = new BogofDiscount(itemAName);

        Assert.assertTrue("Discount SHOULD be applicable", discount.isApplicable(itemA, 2));
        Assert.assertTrue("Discount SHOULD be applicable", discount.isApplicable(itemA, 10));
        Assert.assertFalse("Discount should NOT be applicable", discount.isApplicable(itemA, 1));
        Assert.assertFalse("Discount should NOT be applicable", discount.isApplicable(itemB, 2));
    }

    @Test
    public void testCalculateDiscount()
    {
        Discount discount = new BogofDiscount("Apple");
        long itemPrice = 10;
        int numberOfItems = 0;
        long expectedDiscount = 0;
        long actualDiscount = discount.calculateDiscount(itemPrice, numberOfItems);
        Assert.assertEquals("Expected discount NOT returned", expectedDiscount, actualDiscount);

        numberOfItems = 1;
        expectedDiscount = 0;
        actualDiscount = discount.calculateDiscount(itemPrice, numberOfItems);
        Assert.assertEquals("Expected discount NOT returned", expectedDiscount, actualDiscount);

        numberOfItems = 2;
        expectedDiscount = 10;
        actualDiscount = discount.calculateDiscount(itemPrice, numberOfItems);
        Assert.assertEquals("Expected discount NOT returned", expectedDiscount, actualDiscount);

        numberOfItems = 5;
        expectedDiscount = 20;
        actualDiscount = discount.calculateDiscount(itemPrice, numberOfItems);
        Assert.assertEquals("Expected discount NOT returned", expectedDiscount, actualDiscount);
    }

}

package io.superstore.services;

import io.superstore.data.Basket;
import io.superstore.data.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Test class for the DefaultInventoryService
 */
public class DefaultInventoryServiceTest
{
    DefaultInventoryService inventoryService;

    @Before
    public void setup()
    {
        inventoryService = new DefaultInventoryService();
    }

    @Test
    public void testAddItem()
    {
        String itemName = "Mango";
        Item itemA = new Item(itemName, 35);
        inventoryService.addOrUpdate(itemA);
        Item returnedItem = inventoryService.getItem(itemName);
        Assert.assertEquals("Returned Item is NOT what was added", itemA, returnedItem);
    }

    @Test
    public void testUpdateItem()
    {
        String itemName = "Orange";
        long itemPrice = 20;
        Item originalItem = new Item(itemName, itemPrice);
        Item updatedItem = new Item(itemName, ++itemPrice);

        inventoryService.addOrUpdate(originalItem);
        inventoryService.addOrUpdate(updatedItem);
        Item returnedItem = inventoryService.getItem(itemName);

        Assert.assertNotEquals("The Returned Item should NOT be original", originalItem, returnedItem);
        Assert.assertEquals("The Returned Item SHOULD be updated", updatedItem, returnedItem);
    }

    @Test
    public void testLookupItems()
    {
        String itemNameA = "Mango";
        String itemNameB = "Apple";
        String itemNameC = "Snickers";
        Item itemA = new Item(itemNameA, 24);
        Item itemB = new Item(itemNameB, 25);
        Item itemC = new Item(itemNameC, 69);
        inventoryService.addOrUpdate(itemA);
        inventoryService.addOrUpdate(itemB);
        inventoryService.addOrUpdate(itemC);
        List<String> suppliedItemNames = new LinkedList<>();
        Basket expectedBasket = new Basket();
        expectedBasket.addItem(itemA); suppliedItemNames.add(itemNameA);
        expectedBasket.addItem(itemA); suppliedItemNames.add(itemNameA);
        expectedBasket.addItem(itemB); suppliedItemNames.add(itemNameB);
        expectedBasket.addItem(itemB); suppliedItemNames.add(itemNameB);
        expectedBasket.addItem(itemB); suppliedItemNames.add(itemNameB);
        expectedBasket.addItem(itemC); suppliedItemNames.add(itemNameC);

        Basket actualBasket = inventoryService.lookupItems(suppliedItemNames);
        Assert.assertEquals("Expected basket is NOT what's returned", expectedBasket, actualBasket);
    }

}

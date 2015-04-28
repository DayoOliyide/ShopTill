package io.superstore.services;

import io.superstore.data.Basket;
import io.superstore.data.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A default implementation of the InventoryService.
 */
public class DefaultInventoryService implements InventoryService
{
    Map<String, Item> inventory = new HashMap<>();

    @Override
    public void addOrUpdate(Item i)
    {
        inventory.put(i.getName(), i);
    }

    @Override
    public Item getItem(String name)
    {
        return inventory.get(name);
    }

    @Override
    /**
     * Looks up the provided items from inventory. If the items are known
     * they are then added to the basket that is returned.
     */
    public Basket lookupItems(List<String> itemNames)
    {
        Basket basket = new Basket();
        for(String itemName : itemNames)
        {
            Item item = inventory.get(itemName);
            if(item != null)
            {
                basket.addItem(item);
            }
        }

        return basket;
    }
}

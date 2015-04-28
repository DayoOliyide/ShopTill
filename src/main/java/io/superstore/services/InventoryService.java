package io.superstore.services;

import io.superstore.data.Basket;
import io.superstore.data.Item;

import java.util.List;

/**
 * Represents a service that contains all known items
 */
public interface InventoryService
{
    void addOrUpdate(Item i);

    Item getItem(String name);

    Basket lookupItems(List<String> itemNames);
}

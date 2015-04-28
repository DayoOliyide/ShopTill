package io.superstore;

import io.superstore.data.Basket;
import io.superstore.data.Item;
import io.superstore.discounts.BogofDiscount;
import io.superstore.discounts.ThreeForTwoDiscount;
import io.superstore.services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * A simple application that calculates the price of a basket of shopping.
 */
public class ShopTill
{
    private InventoryService inventoryService;
    private DiscountService discountService;
    private CalculatorService calculatorService;

    public void setInventoryService(InventoryService service)
    {
        this.inventoryService = service;
    }

    public void setDiscountService(DiscountService discountService)
    {
        this.discountService = discountService;
    }

    public void setCalculatorService(CalculatorService calculatorService)
    {
        this.calculatorService = calculatorService;
    }

    public static InventoryService createPrefilledInventoryService()
    {
        InventoryService inventoryS = new DefaultInventoryService();
        Item appleItem = new Item("Apple", 35);
        Item bananaItem = new Item("Banana", 20);
        Item melonItem = new Item("Melon", 50);
        Item limeItem = new Item("Lime", 15);
        inventoryS.addOrUpdate(appleItem);
        inventoryS.addOrUpdate(bananaItem);
        inventoryS.addOrUpdate(melonItem);
        inventoryS.addOrUpdate(limeItem);

        return inventoryS;
    }

    public static DiscountService createPrefilledDiscountService()
    {
        DiscountService discountS = new DefaultDiscountService();
        discountS.addOrUpdate(new BogofDiscount("Melon"));
        discountS.addOrUpdate(new ThreeForTwoDiscount("Lime"));

        return discountS;
    }

    public static void printUsage()
    {
        String usage = "\n                            Welcome to ShopTill\n" +
                "This program calculates the price of a basket of shopping and prints it out\n" +
                "For example  \"Apple Apple Banana\" (without quotes) represents a basket with two apples and one banana.\n" +
                "Items are priced as follows:\n" +
                "                            - Apples are 35p each\n" +
                "                            - Bananas are 20p each\n" +
                "                            - Melons are 50p each, but are available as buy one get one free\n" +
                "                            - Limes are 15p each, but are available in a three for the price two offer\n" +
                "RECOGNIZED ITEMS -> Apple Banana Melon Lime\n" ;
        System.out.println(usage);
    }

    public void start() throws IOException
    {
        printUsage();
        String readLine;
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("> ");
        while(!(readLine = inputReader.readLine()).isEmpty())
        {
            List<String> shoppingList = Arrays.asList(readLine.split(" "));
            long calculatedPrice = calculateShoppingList(shoppingList);
            System.out.println("Total Price -> " + calculatedPrice + "p");
            System.out.print("> ");
        }
    }

    public long calculateShoppingList(List<String> shoppingList)
    {
        Basket shoppingBasket = inventoryService.lookupItems(shoppingList);
        discountService.attachDiscounts(shoppingBasket);
        return calculatorService.calculateTotal(shoppingBasket);
    }

    public static void main(String[] args) throws IOException
    {
        ShopTill theTil = new ShopTill();
        theTil.setInventoryService(createPrefilledInventoryService());
        theTil.setDiscountService(createPrefilledDiscountService());
        theTil.setCalculatorService(new DefaultCalculatorService());

        theTil.start();
    }
}

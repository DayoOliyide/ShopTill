package io.superstore.data;

/**
 * Represents a Basket item
 */
public class Item
{
    private String name;
    private long price;

    public Item(String name, long price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public long getPrice()
    {
        return price;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (price != item.price) return false;
        if (!name.equals(item.name)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = name.hashCode();
        result = 31 * result + (int) (price ^ (price >>> 32));
        return result;
    }
}

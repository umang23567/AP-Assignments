import java.util.HashMap;
import java.util.Map;

public class Cart 
{
    private Map<FoodItem, Integer> items;

    public Cart() 
    {
        items = new HashMap<>();
    }

    public Cart(Map<FoodItem, Integer> items) 
    {
        this.items = new HashMap<>(items);
    }

    public void addItem(FoodItem item, int quantity) 
    {
        items.put(item, items.getOrDefault(item, 0) + quantity);
        System.out.println("Added " + quantity + " " + item.getName() + " to the cart.");
    }

    public void updateQuantity(FoodItem item, int quantity) 
    {
        if (quantity>0)
        {
            items.put(item,quantity);
            System.out.println("Quantity modified to " + quantity + " .");
        }
    }

    public int getQuantity(FoodItem item)
    {
        return items.getOrDefault(item, 0);
    }


    public void removeItem(FoodItem item) 
    {
        if (items.containsKey(item)) 
        {
            items.remove(item);
        } 
        else 
        {
            System.out.println("Item not found in the cart.");
        }
    }

    public boolean isEmpty()
    {
        return (items.isEmpty() || items.values().stream()
                .allMatch(value -> value == 0) );
    }

    public void displayCart() 
    {
        if (items.isEmpty()) 
        {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Items in Cart:");
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) 
        {
            if (entry.getValue()>0)
            {
                System.out.println(entry.getKey() + " - Quantity: " + entry.getValue());
            }
        }
    }

    public void viewTotal() 
    {
        System.out.println("Total Price of Cart: " + getTotal());
    }

    public Map<FoodItem, Integer> getItems() 
    {
        return this.items;
    }

    public void setItems(Map<FoodItem, Integer> items)
    {
        this.items=new HashMap<>(items);
    }

    public void clearCart() 
    {
        items.clear();
        System.out.println("Cart cleared.");
    }
    public void clear() 
    {
        items.clear();
    }

    public double getTotal()
    {
        return this.getItems().entrySet().stream()
                           .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                           .sum();
    }

}

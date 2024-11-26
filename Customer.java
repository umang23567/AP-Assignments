import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable,Comparable<Customer> 
{
    private static final long serialVersionUID = 1L;        // Adding serialVersionUID for version control

    private static int IDGen = 1000;    // ID generator for unique customer IDs

    private boolean isVIP;
    private String name;
    private int customerID;

    private Map<Integer,Order> orderHistory = new HashMap<>();
    private Cart cart; 

    private String password;

    public Customer(String username, String password, String name, boolean vipStatus) 
    {
        super(username, password,name);
        isVIP=vipStatus;
        this.name=name;
        this.customerID=IDGen++;
        this.cart=new Cart();
        this.password=password;
    }

    @Override
    public boolean login(String username, String password) 
    {
        return getUsername().equals(username) && validatePassword(password);
    }


    public int getCustomerID() 
    {
        return customerID;
    }

    public String getName() 
    {
        return name;
    }

    public boolean isVIP() 
    {
        return isVIP;
    }

    public Map<Integer,Order> getOrderHistory()
    {
        return orderHistory;
    }

    public Cart getCart()
    {
        return cart;
    }


    @Override
    public String toString() 
    {
        return 
            "Username: " + getUsername() + "\n" +
            "Customer ID: " + customerID + "\n" +
            "Name: " + name + "\n" +
            "Password: " + password + "\n" +
            "VIP: " + (isVIP?"Yes":"No") + "\n" +
            "Order History:\n" + 
            (orderHistory.isEmpty() ? "No orders yet" : 
            orderHistory.values().stream()
                        .map(Order::toString)
                        .collect(Collectors.joining("\n---\n"))) + "\n" 
            + cart.toString();
    }


    public void removeFromCart(FoodItem item) 
    {
        if (cart.getItems().containsKey(item))
        {
            cart.removeItem(item);
        }
    }

    private transient Scanner scanner=new Scanner(System.in);
    private Scanner getScanner() 
    {
        if (scanner == null) 
        {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    private int choice;
    
    public void browseMenu(Map<String,FoodItem> menu) 
    {
        System.out.println("1. View all Items");
        System.out.println("2. Search Functionality");
        System.out.println("3. Filer by Category");
        System.out.println("4. Sort by Price");
        choice=getScanner().nextInt();
        getScanner().nextLine();

        switch (choice)
        {
            case 1 -> viewAllItems(menu);

            case 2 -> searchFunctionality(menu);

            case 3 -> filterByCategory(menu);

            case 4 -> sortByPrice(menu);

            default -> System.out.println("Invalid option.");
            
        }
    }

    public void viewAllItems(Map<String,FoodItem> menu)
    {
        for (FoodItem item: menu.values())
        {
            if (item.isAvailable())
            {
                System.out.println(item + " " + "Available");    
            }
            else
            {
                System.out.println(item + " " + "Not available");    
            }
        }
        
    }

    public void searchFunctionality(Map<String,FoodItem> menu)
    {   
        System.out.println("Enter item name to search: ");
        String keyWord=getScanner().nextLine().toLowerCase();

        boolean found = false;
        for (FoodItem item: menu.values())
        {
            if (item.getName().toLowerCase().contains(keyWord)) 
            {
                if (!found)
                {
                    System.out.println("Search Results:");
                    found=true;
                }
                System.out.println(item + " " + (item.isAvailable()?"Available":"Not available"));    
            }
        }
        if (!found) 
        {
            System.out.println("No items found matching: " + keyWord);
        }
    }

    public void filterByCategory(Map<String,FoodItem> menu)
    {   
        System.out.print("Enter category to filter by: ");
        String category=getScanner().nextLine().toLowerCase();
    
        boolean found = false;
        for (FoodItem item: menu.values())
        {
            if (item.getCategory().toLowerCase().contains(category)) 
            {
                if (!found)
                {
                    System.out.println("Items in category '" + category + "':");
                    found = true;
                }
                System.out.println(item + " " + (item.isAvailable()?"Available":"Not available"));    
            }
        }
        if (!found) 
        {
            System.out.println("No items found in category: " + category);
        }
    }

    public void sortByPrice(Map<String,FoodItem> menu)
    {
        System.out.println("Sort by price");
        System.out.println("1. Ascending ");
        System.out.println("2. Descending ");
        int sortChoice = getScanner().nextInt();
        getScanner().nextLine();
    
        // Convert map values to a list for sorting
        List<FoodItem> sortedList = new ArrayList<>(menu.values());
    
        switch (sortChoice)
        {
            case 1 -> sortedList.sort(Comparator.comparingDouble(FoodItem::getPrice));
            
            case 2 -> sortedList.sort(Comparator.comparingDouble(FoodItem::getPrice).reversed());
            
            default -> 
            {
                System.out.println("Invalid choice.");
                return;
            }
        }
    
        System.out.println("Menu items sorted by price:");
        for (FoodItem item : sortedList) 
        {
            System.out.println(item + " " + (item.isAvailable()?"Available":"Not available"));    
        }

    }

    public void cartOptions(Map<String,FoodItem> menu, Admin admin)
    {
        System.out.println("1. View Cart");
        System.out.println("2. Add Items");
        System.out.println("3. Modify Quantities");
        System.out.println("4. Remove Items");
        System.out.println("5. View Total");
        System.out.println("6. Checkout Process");
        System.out.println("7. Clear Cart");
        choice=getScanner().nextInt();
        getScanner().nextLine();

        switch (choice)
        {
            case 1 -> cart.displayCart();

            case 2 -> addItems(menu);

            case 3 -> modifyQuantities(menu);

            case 4 -> removeItems();

            case 5 -> cart.viewTotal();

            case 6 -> checkoutProcess(cart,admin);

            case 7 -> cart.clearCart();
       
            default -> System.out.println("Invalid option.");
        }

    }
    
    public void addToCart(FoodItem f, int q)
    {
        cart.addItem(f,q);
    }

    public void addItems(Map<String,FoodItem> menu) 
    {
        boolean found=false;
        for (FoodItem item: menu.values())
        {
            if (item.isAvailable())
            {
                if (!found)
                {
                    System.out.println("Available Menu Items:");
                    found=true;
                }
                System.out.println(item);
            }
        }

        if (!found)
        {
            System.out.println("No itmes available in Menu.");
            return;
        }

        System.out.print("Enter the name of the item to add: ");
        String itemName = getScanner().nextLine();
        
        FoodItem selectedItem = menu.values().stream()
                                    .filter(item -> item.getName().equalsIgnoreCase(itemName))
                                    .findFirst()
                                    .orElse(null);
    
        if (selectedItem != null) 
        {
            if (selectedItem.isAvailable())
            {
                System.out.print("Enter quantity: ");
                int quantity = getScanner().nextInt();
                getScanner().nextLine();
                if (quantity>0)
                {
                    cart.addItem(selectedItem, quantity);
                }
                else
                {
                    System.out.println("Positive quantity needed.");
                }
            }
            else
            {
                System.out.println("Item not available.");
            }
        } 
        else 
        {
            System.out.println("Item not found in the menu.");
        }
    }
    

    public void modifyQuantities(Map<String,FoodItem> menu) 
    {
        if (!cart.isEmpty())
        {
            System.out.println("Current Cart Items:");
            cart.displayCart();

            System.out.println("Enter the name of the item to modify: ");
            String itemName = getScanner().nextLine();

            FoodItem itemInCart = cart.getItems().keySet().stream()
                                    .filter(item -> item.getName().equalsIgnoreCase(itemName))
                                    .findFirst()
                                    .orElse(null);
                            
            int originalQuantity=cart.getQuantity(itemInCart);
            if (itemInCart != null) 
            {
                System.out.println("Enter new quantity: ");
                int newQuantity = getScanner().nextInt();
                getScanner().nextLine();
                if (newQuantity>0)
                {
                    if (newQuantity<=originalQuantity || itemInCart.isAvailable())
                    {
                        cart.updateQuantity(itemInCart, newQuantity);
                        System.out.println("Updated quantity of " + itemInCart.getName() + " to " + newQuantity);
                    }
                    else
                    {
                        System.out.println("Item unavailable. Cannot update to higher quantity.");
                    }
                }
                else
                {
                    System.out.println("Positive quantity needed.");
                }
            } 
            else 
            {
                System.out.println("Item not found in the cart.");
            }
        }
        else
        {
            System.out.println("Cart is empty. Cannot modify quantities.");
        }
    }
    

    public void removeItems() 
    {
        if (!cart.isEmpty())
        {
            System.out.println("Current Cart Items:");
            cart.displayCart();
        
            System.out.println("Enter the name of the item to remove: ");
            String itemName = getScanner().nextLine();
        
            FoodItem itemToRemove = cart.getItems().keySet().stream()
                                        .filter(item -> item.getName().equalsIgnoreCase(itemName))
                                        .findFirst()
                                        .orElse(null);
        
            if (itemToRemove != null) 
            {
                cart.removeItem(itemToRemove);
                System.out.println("Removed " + itemName + " from the cart.");
            } 
            else 
            {
                System.out.println("Item not found in the cart.");
            }
        }
        else
        {
            System.out.println("Cart is empty. Cannot remove items.");
        }
    }
    
    //
    public Order checkout()
    {
        for (FoodItem foodItem: cart.getItems().keySet())
        {
            if (!foodItem.isAvailable())
            {
                System.out.println(foodItem.getName() + " is unavailable. ");
                return null;
            }
        }

        return new Order(cart.getTotal(),cart.getItems(),isVIP,null);
    }

    public void checkoutProcess(Cart checkoutCart, Admin admin)
    {
        for (FoodItem foodItem: checkoutCart.getItems().keySet())
        {
            if (!foodItem.isAvailable())
            {
                checkoutCart.removeItem(foodItem);
            }
        }

        if (!checkoutCart.isEmpty())
        {
            checkoutCart.viewTotal();
            System.out.println("Do you want to checkout? (Y/N)");
            String re=getScanner().nextLine();
            if (re.equalsIgnoreCase("Y"))
            {
                Order currentOrder=new Order(checkoutCart.getTotal(),checkoutCart.getItems(),isVIP,null);
                System.out.println("Do you have any special request? (Y/N)");
                String Re=getScanner().nextLine();
                if (Re.equalsIgnoreCase("Y"))
                {
                    System.out.println("Enter special request: ");
                    String spec=getScanner().nextLine();
                    System.out.println("Ok");
                    currentOrder.setSpecialRequest(spec);
                }
                System.out.println("Enter delivery address: ");
                String address=getScanner().nextLine();
                System.out.println("Proceeding with checkout...");
                System.out.println("Checkout completed. Thank you for your order!");
                checkoutCart.clear();
                System.out.println("Your order ID is "+currentOrder.getOrderId());
                currentOrder.setAddress(address);
                orderHistory.put(currentOrder.getOrderId(),currentOrder);   
                admin.addOrder(currentOrder);
            }
        }
        else
        {
            System.out.println("Cart is empty. Cannot checkout.");
        }
    }

    public void trackOrder(Admin admin)
    {
        System.out.println("1. View Order Status");
        System.out.println("2. Cancel Order");
        System.out.println("3. View Order History");
        choice=getScanner().nextInt();
        getScanner().nextLine();

        switch (choice)
        {
            case 1 -> viewOrderStatus();

            case 2 -> cancelOrder(admin);

            case 3 -> viewOrderHistory(admin);

            default -> System.out.println("Invalid option.");
        }
    }

    public void viewOrderStatus()
    {   
        System.out.println("Enter order ID: ");
        int orderId=getScanner().nextInt();
        Order orderFound=orderHistory.get(orderId);
        getScanner().nextLine();
      
        if (orderFound!=null) 
        {
            System.out.println("Order Status: " + orderFound.getStatus());
        }
        else
        {
            System.out.println("Order not found.");
        }
    }

    public void cancelOrder(Admin admin)
    {
        System.out.println("Enter order ID: ");
        int orderId=getScanner().nextInt();
        boolean found=false;
        boolean processed=false;
        getScanner().nextLine();

        Order orderFound=orderHistory.get(orderId);
        if (orderFound!=null) 
        {
            String status=orderFound.getStatus();
            if (status.equalsIgnoreCase("Pending") || status.equalsIgnoreCase("Preparing"))
            { 
                admin.RemoveOrder(orderFound,"Cancelled");
            }
            else
            {
                processed=true;
            }
            found=true;
        }

        if (!found)
        {
            System.out.println("Order not found.");
        }
        else if (processed)
        {
            System.out.println("Order already processed. Cannot cancel!");
        }
    }

    public void viewOrderHistory(Admin admin)
    {
        if (!orderHistory.isEmpty())
        {
            System.out.println("Order History: ");
            orderHistory.values().stream()
            .sorted(Comparator.comparing(Order::getOrderDateTime))
            .forEach(System.out::println); 

            System.out.println("Do you want to re order? (Y/N)");
            String re=getScanner().nextLine();
            if (re.equalsIgnoreCase("Y"))
            {
                System.out.println("Enter order ID");
                int orderID=getScanner().nextInt();
                Order orderFound=orderHistory.get(orderID);
                getScanner().nextLine();
                if (orderFound==null)
                {
                    System.out.println("Order not found.");
                }
                else
                {
                    checkoutProcess(new Cart(orderFound.orderItems()),admin);
                }
            }
        }
        else 
        {
            System.out.println("Order History empty.");
        }
    }

    public void reviewItems(Map<String,FoodItem> menu)
    {
        System.out.println("1. Provide Review");
        System.out.println("2. View Reviews");
        choice=getScanner().nextInt();
        getScanner().nextLine();

        switch (choice)
        {
            case 1 -> provideReviews(menu);

            case 2 -> viewReviews(menu);

            default -> System.out.println("Invalid option.");
        }
    }

    public void provideReviews(Map<String,FoodItem> menu)
    {
        System.out.print("Enter the name of the item: ");
        String itemName = getScanner().nextLine();
    
        FoodItem itemFound = menu.values().stream()
                          .filter(item -> item.getName().equalsIgnoreCase(itemName))
                          .findFirst()
                          .orElse(null);

    
        if (itemFound != null) 
        {
            System.out.println("Enter rating for item (1-5): ");
            int rating=(int)getScanner().nextDouble();
            rating=(rating<1)?1:(rating>5)?5:rating;
            System.out.println("Enter feedback for item");
            getScanner().nextLine();
            String feedback=getScanner().nextLine();
            itemFound.addFeedback(rating,feedback);
        } 
        else 
        {
            System.out.println("Item not found.");
        }
    }

    public void viewReviews(Map<String,FoodItem> menu)
    {
        System.out.print("Enter the name of the item: ");
        String itemName = getScanner().nextLine();
    
        FoodItem itemFound = menu.values().stream()
                          .filter(item -> item.getName().equalsIgnoreCase(itemName))
                          .findFirst()
                          .orElse(null);

    
        if (itemFound!= null) 
        {
            System.out.print("Item Rating: ");
            double rating=itemFound.getRating();
            if (rating==0.0)
            {
                System.out.println("--");
            }
            else
            {
                System.out.printf("%.2f%n", rating);
            }
            
            List<String> feedbacks=itemFound.getFeedbacks();
            if (!feedbacks.isEmpty())
            {
                System.out.println("Item Reviews: ");
                for (String feedback: feedbacks)
                {
                    System.out.println(feedback);
                }
            } 
            else
            {
                System.out.println("No reviews yet.");
            }
        }
        else 
        {
            System.out.println("Item not found.");
        }
    }

    @Override
    public int compareTo(Customer other) 
    {
        return Integer.compare(this.customerID, other.customerID);
    }
}

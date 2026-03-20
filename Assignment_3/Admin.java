import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;


public class Admin extends User 
{
    private static Admin instance;      
    //single instance only

    private Map<String, FoodItem> menu = new HashMap<>();

    private Map<Integer,Order> orderHistory = new HashMap<>();
    // contains all orders

    private PriorityQueue<Order> orderList= new PriorityQueue<>();                                 
    // contains only Pending/Preparing orders

    private Map<LocalDate,Double> totalSales=new HashMap<>();

    private int order_cnt=0;

    private Admin(String username, String password) 
    {
        super(username, password);

        // Hardcoded data
        menu.put("Pizza",new FoodItem("Pizza", 200, "Fast food"));
        menu.put("Burger",new FoodItem("Burger", 50, "Fast food"));
        menu.put("Dosa",new FoodItem("Dosa", 80, "South Indian"));
        menu.put("Idli",new FoodItem("Idli", 40, "South Indian"));
    }

    public Map<String, FoodItem> getMenu()
    {
        return menu;
    }

    public void increase_order_cnt()
    {
        order_cnt++;
    }
    public void decrease_order_cnt()
    {
        order_cnt--;
    }

    public Map<Integer,Order> getOrderHistory()
    {
        return orderHistory;
    }

    public PriorityQueue<Order> getOrderList()
    {
        return orderList;
    }

    public void updateTotalSales(double amount)
    {
        LocalDate currentDate = LocalDate.now();
        totalSales.put(currentDate, totalSales.getOrDefault(currentDate, 0.0) + amount);
    }


    public double getTotalSales(LocalDate date)
    {
        return totalSales.getOrDefault(date, 0.0); 
    }

    public static Admin getAdmin(String username, String password) 
    {
        if (instance==null) 
        {
            instance=new Admin(username, password);
        }
        return instance;
    }

    @Override
    public boolean login(String username, String password) 
    {
        return getUsername().equals(username) && validatePassword(password);
    }

    public void addOrder(Order order)
    {
        orderHistory.put(order.getOrderId(),order);
        orderList.add(order);
        increase_order_cnt();
        updateTotalSales(order.getTotal());
    }

    public void RemoveOrder(Order order, String newStatus)
    {
        order.setStatus(newStatus);
        orderList.remove(order); 
        decrease_order_cnt();
        System.out.println("Order "+ order.getOrderId() + " " + newStatus.toLowerCase() + ".");
    }

    private Scanner scanner=new Scanner(System.in);
    private int choice;

    public void manageMenu(TreeSet<Customer> customers)
    {
        System.out.println("1. Add new Items");
        System.out.println("2. Update existing Items");
        System.out.println("3. Remove Items");
        choice=scanner.nextInt();
        scanner.nextLine();

        switch (choice)
        {
            case 1 -> addItems();

            case 2 -> updateItems();

            case 3 -> removeItems(customers);

            default -> System.out.println("Invalid option.");          
        }
    } 

    public void addItems()
    {
        System.out.println("Enter name of item: ");
        String name=scanner.nextLine();
        if (menu.get(name)==null)
        {
            System.out.println("Enter price of item: ");
            int price=scanner.nextInt();
            System.out.println("Enter category of item: ");
            scanner.nextLine();
            String category=scanner.nextLine();
            menu.put(name,new FoodItem(name,price,category));
        }
        else
        {
            System.out.println("Item already exists.");
        }
    }

    public void updateItems()
    {
        System.out.println("Enter name of item: ");
        String name=scanner.nextLine();
        FoodItem itemFound=menu.get(name);
        if (itemFound!=null)
        {
            System.out.println("1. Update Price");
            System.out.println("2. Update Availability");
            choice=scanner.nextInt();
            scanner.nextLine();
            switch(choice)
            {
                case 1 ->
                {   
                    System.out.println("Enter new Price: ");
                    double newPrice=scanner.nextDouble();
                    itemFound.setPrice(newPrice);
                }
                case 2 ->
                {
                    System.out.println("Available? (Y/N)");
                    String inp=scanner.nextLine();
                    itemFound.changeAvailability(inp.equalsIgnoreCase("Y"));
                }

                default -> System.out.println("Invalid option.");
            }
        }
        else
        {
            System.out.println("Item not found.");
        }
    }

    public void removeItems(TreeSet<Customer> customers)
    {
        System.out.println("Enter name of item: ");
        String name=scanner.nextLine();
        FoodItem itemFound=menu.get(name);
        if (itemFound!=null)
        {
            menu.remove(name);
            System.out.println("Item removed from menu.");
            Iterator<Order> iterator = orderList.iterator();
            List<Order> ordersToRemove = new ArrayList<>();
            while (iterator.hasNext()) 
            {
                Order order = iterator.next();
                Integer quantity = order.orderItems().getOrDefault(itemFound,0);
                if (quantity>0) 
                {
                    if (order.getStatus().equalsIgnoreCase("Pending") || order.getStatus().equalsIgnoreCase("Preparing"))
                    {
                        ordersToRemove.add(order);
                    }
                } 
            }
            for (Order order : ordersToRemove) 
            {
                RemoveOrder(order, "Denied");
            }

            for (Customer customer: customers) 
            {
                customer.removeFromCart(itemFound);
            }
        }
        else
        {
            System.out.println("Item not found.");
        }
    }

    public void manageOrder()
    {
        System.out.println("1. View Order History for Today");
        System.out.println("2. View pending Orders");
        System.out.println("3. Update Order status");
        System.out.println("4. Process Refunds");
        choice=scanner.nextInt();
        scanner.nextLine();

        switch (choice)
        {
            case 1 -> viewOrderHistory();

            case 2 -> viewPendingOrders();

            case 3 -> updateOrderStatus();

            case 4 -> processRefunds();

            default -> System.out.println("Invalid option.");
        }
    }

    public void viewOrderHistory()
    {
        if (!orderHistory.isEmpty())
        {
            System.out.println("Order History for today: ");
            LocalDate today = LocalDate.now();
            orderHistory.values().stream()
            .filter(order -> order.getOrderDateTime().toLocalDate().equals(today)) 
            .sorted(Comparator.comparing(Order::getOrderDateTime))
            .forEach(System.out::println); 
        }
        else
        {
            System.out.println("Order History empty.");
        }
    }

    public void viewPendingOrders()
    {
        if (!orderList.isEmpty())
        {
            System.out.println("Pending Orders: ");
            orderList.stream()
                    .sorted()
                    .forEach(System.out::println);
        }
        else
        {
            System.out.println("No Pending orders.");
        }
    }

    public void updateOrderStatus()
    {
        System.out.println("Enter order ID: ");
        int orderId=scanner.nextInt();
        scanner.nextLine();
        Order orderFound=orderHistory.get(orderId);
        if (orderFound==null)
        {
            System.out.println("Order not found.");
        }
        else
        {
            System.out.println("Enter new status: ");
            String newStatus=scanner.nextLine();
            if (orderFound.getStatus().equalsIgnoreCase(newStatus))
            {
                System.out.println("Status already set.");
                return;
            }

            if (orderFound.getStatus().equalsIgnoreCase("Pending") || orderFound.getStatus().equalsIgnoreCase("Preparing"))
            {
                if (newStatus.equalsIgnoreCase("Cancelled") || newStatus.equalsIgnoreCase("Denied"))
                {
                    RemoveOrder(orderFound,newStatus);
                }
                else 
                {
                    orderFound.setStatus(newStatus);
                }
                System.out.println("Status for order"+orderFound.getOrderId()+" updated to "+newStatus+".");  
            }    
            else
            {
                if (newStatus.equalsIgnoreCase("Pending") || newStatus.equalsIgnoreCase("Preparing"))
                {
                    System.out.println("Order processed. Cannot update to " + newStatus + " status.");
                }
                else
                {
                    orderFound.setStatus(newStatus);
                    System.out.println("Status for order"+orderFound.getOrderId()+" updated to "+newStatus+".");  
                }
            }
            
            if (newStatus.equalsIgnoreCase("Preparing"))
            {
                String request=orderFound.getSpecialRequest();
                if (request!=null)
                {
                    System.out.println("Preparing with special request: " + request);
                }
            }
        }
    }

    public void processRefund(Order order)
    {
        order.setStatus("Refunded");
        updateTotalSales((-1)*order.getTotal());
        System.out.println("Order"+order.getOrderId()+" refunded.");
        
    }

    public boolean refundsPending()
    {
        for (Order order: orderHistory.values())
        {
            if (order.getStatus().equalsIgnoreCase("Denied") || order.getStatus().equalsIgnoreCase("Cancelled"))
            {
                return true;
            }
        }
        return false;
    }

    public void processRefunds()
    {
        boolean once=false;
        for (Order order: orderHistory.values())
        {
            if (order.getStatus().equalsIgnoreCase("Denied") || order.getStatus().equalsIgnoreCase("Cancelled"))
            {
                processRefund(order);
                if (!once) 
                {
                    once=true;
                }
            }
        }
        if (once)
        {
            System.out.println("All refunds processed."); 
        }   
        else
        {
            System.out.println("No refunds pending.");
        }    
    }

    // public void handleSpecialRequest()
    // {
    //     System.out.println("Enter order ID: ");
    //     int orderId=scanner.nextInt();
    //     scanner.nextLine();
    //     Order orderFound=orderHistory.get(orderId);
    //     if (orderFound==null)
    //     {
    //         System.out.println("Order not found.");
    //     }
    //     else
    //     {
    //         String sr=orderFound.getSpecialRequest();
    //         if (sr==null)
    //         {
    //             System.out.println("No special request");
    //         }
    //         else
    //         {
    //             System.out.println("Request: " + sr + " handled.");
    //         }
    //     }
    // }

    public void generateReport()
    {
        if (refundsPending())
        {
            System.out.println("Do you first want to process refunds? (Y/N)");
            String re=scanner.nextLine();
            if (re.equalsIgnoreCase("Y"))
            {
                processRefunds();
            }
        }
        System.out.println("Report for today: ");
        LocalDate currentDate = LocalDate.now();
        System.out.println("Total income for the day= "+ totalSales.getOrDefault(currentDate, 0.0));
        System.out.println("Total orders for the day= " + order_cnt );
    }

}

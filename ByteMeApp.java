import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class ByteMeApp
{
    private static TreeSet<Customer> customers;
    private static Admin admin;

    private transient static Scanner scanner = new Scanner(System.in);
    private static int choice;

    //
    public static Customer CustomerLogin(String username, String password, List<Customer> customersList)
    {
        Customer customer=  customersList.stream()
                            .filter(c->c.getUsername().equalsIgnoreCase(username))
                            .findFirst()
                            .orElse(null);
        if (customer != null && customer.login(username, password)) 
        {
            return customer;
        } 
        else 
        {
            System.out.println("Invalid customer credentials!");
            return null;
        }
    }

    public static void main(String[] args)
    {
        admin=SerializationManager.loadAdmin(); 
        
        customers=SerializationManager.loadCustomers(); 

        System.out.println("\u001B[1m"+"Welcome to "+"\u001B[4m"+"Byte Me! Food Ordering System"+"\u001B[0m");

        while (true) 
        {   
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");

            choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice) 
            {
                case 1 -> adminLogin();
                case 2 -> customerLogin();
                case 3 ->
                {
                    SerializationManager.saveState(admin,customers);
                    System.out.println("\u001B[1m"+"Thank you for using Byte Me!"+"\u001B[0m");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again!");
            }
        }
    }

    private static void adminLogin() 
    {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (admin.login(username,password)) 
        {
            System.out.println("Admin login successful.");
            adminMenu(admin);
        } 
        else
        {
            System.out.println("Invalid admin credentials!");
        }
    }

    private static void customerLogin() 
    {
        System.out.println("1. Register");
        System.out.println("2. Login");
        choice=scanner.nextInt();
        scanner.nextLine();
        switch (choice) 
        {
            case 1 -> {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Are you a VIP user? (Y/N): ");
                String inp=scanner.nextLine();
                boolean isVIP = inp.equalsIgnoreCase("Y");

                if (customers.stream()
                    .filter(c->c.getUsername().equalsIgnoreCase(username))
                    .findFirst()
                    .orElse(null) == null)
                {
                    Customer customer=new Customer(username, password, name, isVIP);
                    customers.add(customer);

                    customerMenu(customer);
                }
                else
                {
                    System.out.println("Username already exits.");
                }
            }

            case 2 -> 
            {
                System.out.print("Enter username: ");
                String username=scanner.nextLine();
                System.out.print("Enter password: ");
                String password=scanner.nextLine();

                Customer customer=  customers.stream()
                                     .filter(c->c.getUsername().equalsIgnoreCase(username))
                                     .findFirst()
                                     .orElse(null);
                if (customer != null && customer.login(username, password)) 
                {
                    System.out.println("Customer login successful.");
                    customerMenu(customer);
                } 
                else 
                {
                    System.out.println("Invalid customer credentials!");
                }
                                   
            }  

            default -> System.out.println("Invalid option. Please try again!");    
        }    
        
    }
    
    private static void adminMenu(Admin admin) 
    {
        System.out.println("----Admin Menu----");
        while (true)
        {
            System.out.println("1. Menu Management");
            System.out.println("2. Order Management");
            System.out.println("3. Report Generation");
            System.out.println("4. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) 
            {
                case 1 -> admin.manageMenu(customers);
                
                case 2 -> admin.manageOrder();
        
                case 3 -> admin.generateReport();
                    
                case 4 -> 
                {
                    System.out.println("Exiting Admin mode...");
                    return;
                }
                
                default -> System.out.println("Invalid option.");      
            }
        }
    }

    private static void customerMenu(Customer customer) 
    {
        System.out.println("---Customer Menu---");
        while (true)
        {
            System.out.println("1. Browse Menu");
            System.out.println("2. Cart Options");
            System.out.println("3. Order Tracking");
            System.out.println("4. Item Reviews");
            System.out.println("5. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) 
            {      
                case 1 -> 
                {
                    // customer.browseMenu(admin.getMenu()); 
                    new MenuGUI(admin.getMenu());
                }

                case 2 -> customer.cartOptions(admin.getMenu(),admin);

                case 3 -> customer.trackOrder(admin);

                case 4 -> customer.reviewItems(admin.getMenu());
                
                case 5 -> 
                {
                    System.out.println("Exiting Customer mode...");
                    return;
                }
                    
                default -> System.out.println("Invalid option.");
            }
        }
    }
}



import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class ByteMeApp
{
    // Hardcoded data
    private static Admin admin = Admin.getAdmin("admin@mail.com","admin");
    private static TreeSet<Customer> customers = new TreeSet<>(Comparator.comparing(Customer::getCustomerID));
    static 
    {
        customers.add(new Customer("c1@mail.com","c1","customer1","VIP"));
        customers.add(new Customer("c2@mail.com","c2","customer2","Regular"));
        customers.add(new Customer("c3@mail.com","c3","customer3","Regular"));
    }

    // Implementation 
    private static Scanner scanner = new Scanner(System.in);
    private static int choice;

    public static void main(String[] args) 
    {
        System.out.println("Welcome to Byte Me! Food Ordering System");
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
                    System.out.println("Thank you for using Byte Me!");
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

        if (admin.login(username, password)) 
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
        System.out.print("Enter customer username: ");
        String username = scanner.nextLine();
        System.out.print("Enter customer password: ");
        String password = scanner.nextLine();

        // Find the customer by username
        Customer customer = customers.stream()
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
                case 1 -> customer.browseMenu(admin.getMenu());

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



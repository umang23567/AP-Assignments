import java.io.*;
import java.util.*;

public class SerializationManager {

    private static final String DATA_DIR = "data";

    private static final String ADMIN_FILE = "data/admin.dat";
    private static final String CUSTOMER_FILE = "data/customers.dat";

    static 
    {

        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) 
        {
            boolean created = dataDir.mkdir();
            if (created) 
            {
                System.out.println("\u001B[2m"+"Created 'data' directory."+"\u001B[0m");
            } 
            else 
            {
                System.out.println("\u001B[2m"+"Failed to create 'data' directory."+"\u001B[0m");
            }
        }
    }

    // Save Admin and Customers to file
    public static void saveState(Admin admin, TreeSet<Customer> customers) 
    {
        try (ObjectOutputStream adminOut = new ObjectOutputStream(new FileOutputStream(ADMIN_FILE));
             ObjectOutputStream customerOut = new ObjectOutputStream(new FileOutputStream(CUSTOMER_FILE))) 
        {

            // Serialize Admin
            adminOut.writeObject(admin);
            System.out.println("\u001B[2m"+"Admin database updated."+"\u001B[0m");

            // Serialize List of Customers
            customerOut.writeObject(customers);
            System.out.println("\u001B[2m"+"Customer database updated."+"\u001B[0m");

        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // Load Admin from file
    public static Admin loadAdmin() 
    {
        File adminFile = new File(ADMIN_FILE);
        if (adminFile.exists()) 
        {
            try (ObjectInputStream adminIn = new ObjectInputStream(new FileInputStream(ADMIN_FILE))) 
            {
                System.out.println("\u001B[2m"+"Admin data loaded successfully."+"\u001B[0m");
                return (Admin) adminIn.readObject();
            } 
            catch (IOException | ClassNotFoundException e) 
            {
                e.printStackTrace();
            }
        } 
        else 
        {
            System.out.println("\u001B[2m"+"No admin data found, creating new database."+"\u001B[0m");
        }
        return Admin.getAdmin("admin@mail.com","admin");
    }

    // Load Customers from file
    public static TreeSet<Customer> loadCustomers() 
    {
        File customerFile = new File(CUSTOMER_FILE);
        if (customerFile.exists()) 
        {
            try (ObjectInputStream customerIn = new ObjectInputStream(new FileInputStream(CUSTOMER_FILE))) 
            {
                System.out.println("\u001B[2m"+"Customer data loaded successfully."+"\u001B[0m");
                return (TreeSet<Customer>) customerIn.readObject();
            }
            catch (IOException | ClassNotFoundException e) 
            {
                e.printStackTrace();
            }
        } 
        else 
        {
            System.out.println("\u001B[2m"+"No customer data found, creating new database."+"\u001B[0m");
        }
        return new TreeSet<>(); // Return an empty TreeSet if file does not exist
    }
}

import java.io.*;
import java.util.TreeSet;

public class DatFileInspector 
{
    public static void main(String[] args) 
    {
        String adminFilePath = "data/admin.dat";
        String customersFilePath = "data/customers.dat";

        System.out.println("\u001B[1m"+"Admin Data:\n"+"\u001B[0m");
        Admin admin = (Admin) loadObject(adminFilePath);
        if (admin != null) 
        {
            System.out.println(admin);
        }

        System.out.println("\u001B[1m"+"\nCustomer Data:\n"+"\u001B[0m");
        TreeSet<Customer> customers = (TreeSet<Customer>) loadObject(customersFilePath);
        if (customers != null) 
        {
            for (Customer customer : customers) 
            {
                System.out.println(customer);
            }
        }
    }

    private static Object loadObject(String filePath) 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) 
        {
            return ois.readObject();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found: " + filePath);
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        return null;
    }

}

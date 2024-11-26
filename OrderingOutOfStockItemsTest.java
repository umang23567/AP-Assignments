import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

class OrderingOutOfStockItemsTest 
{
    private Customer customer1;
    private Admin admin;
    FoodItem f1,f2;

    @BeforeEach
    void setUp() 
    {
        // Initialize the system with some sample items
        customer1 = new Customer("john_doe","pass123","john doe",false);
        admin= new Admin("admin@mail.com", "admin");
        
        f1=new FoodItem("Burger",50,"Fast food");
        f2=new FoodItem("Pizza",100,"Fast food");

        admin.addToMenu(f1);
        admin.addToMenu(f2);

        customer1.addToCart(f1,2);
        customer1.addToCart(f2,1);
    }

    @Test
    void testOrderInStockItem() 
    {
        f1.changeAvailability(true);
        f2.changeAvailability(true);

        Order result=customer1.checkout();
        assertNotNull(result, "Order should be created when all items are available.");
    }

    @Test
    void testOrderOutOfStockItem() 
    {
        f2.changeAvailability(false);

        Order result=customer1.checkout();
        assertNull(result, "Order should not be created when an item is out of stock.");

    }


}

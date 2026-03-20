import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class InvalidLoginTest 
{
    private List<Customer> customersList;

    @BeforeEach
    void setUp() 
    {
        // Set up test data
        customersList = new ArrayList<>();
        customersList.add(new Customer("john_doe", "pass123","john doe",true));
        customersList.add(new Customer("jane_smith", "pass456","jane smith",true));
    }

    @Test
    void testValidCredentials() 
    {
        Customer customer = ByteMeApp.CustomerLogin("john_doe", "pass123",customersList);
        assertNotNull(customer, "Login should succeed for valid credentials.");
        assertEquals("john_doe", customer.getUsername(), "The username should match the logged-in customer.");
    }

    @Test
    void testInvalidCredentials() 
    {
        Customer customer = ByteMeApp.CustomerLogin("wrong username", "wrong password", customersList);
        assertNull(customer, "Login should fail for invalid credentials.");
    }

}

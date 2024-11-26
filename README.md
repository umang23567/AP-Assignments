### Assignment-4

---

### **GUI Features**

#### 1. **Menu GUI**  
The **Menu GUI** is triggered via CLI options and provides a graphical view of the available food items, displaying details such as name, price, and availability.  
- **Relaunching**:  
  Once the menu is closed, you need to re-launch it using the corresponding CLI option.  
- **Refresh Mechanism**:  
  This GUI does not have a direct refresh button. To reflect changes in the menu (like updated prices or availability), you need to relaunch the menu GUI from the CLI after making modifications.

#### 2. **Pending Orders GUI**  
The **Pending Orders GUI** displays a table of orders currently in a pending or preparing state. It includes the following features:  
- **Columns Displayed**: Order ID, items ordered, and current status.  
- **Refresh Mechanism**:  
  - A refresh button is provided in the GUI to update the table with the latest pending orders without relaunching the GUI.  
  - Alternatively, you can relaunch the GUI from the CLI option for a fresh view.  

---

### **JUnit Testing**

#### 1. **Testing Ordering Out-of-Stock Items**  
JUnit tests simulate the scenario where a customer attempts to order items that are marked as out of stock.  
- The test ensures the following:  
  1. An appropriate error message is shown to the user.  
  2. The system prevents the order from being added to the admin's order history.  
- **Test Flow**:  
  - Add an out-of-stock item to the cart.  
  - Attempt to proceed to checkout.  
  - Verify that an exception is thrown and no order is processed.  

#### 2. **Testing Invalid Login Attempts**  
JUnit tests validate the system's behavior when incorrect login credentials are used by admins or customers.  
- The test ensures the following:  
  1. The system denies access for invalid usernames or passwords.  
  2. An appropriate error message is displayed.  
- **Test Flow**:  
  - Simulate invalid admin login attempts (e.g., wrong username/password).  
  - Simulate invalid customer login attempts for unregistered users.  
  - Verify that the system returns the correct error state and does not allow access.  

#### **Testing Notes**:  
- For consistency, data is cleared or reset between tests using `@BeforeEach` to ensure isolated testing.  
- Temporary data is used during tests to prevent interference with serialized files or existing system state.  

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
JUnit tests simulate the scenario where a customer attempts to order items that are marked as unavailable.  
- The test ensures the following:  
  1. An appropriate error message is shown to the user.  
  2. The system prevents the order from being added to the admin's order history.  
- **Test Flow**:  
  - Add an unavailable item to the cart.  
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

---

### **File Management and Persistence**  
- **Serialized Data**:  
  The application uses Java's `ObjectOutputStream` and `ObjectInputStream` to handle data persistence. The `.dat` files are binary files and not human-readable.
  - `admin.dat`: Contains the serialized `Admin` instance with all associated data (menu, orders, etc.).  
  - `customers.dat`: Contains a `TreeSet<Customer>` with all customer information and their associated data 

- **Loading Data**:  
  At startup, the system deserializes the `admin.dat` and `customers.dat` files to restore all data.  
  Example:
  ```java
  private static Admin admin = SerializationManager.loadAdmin();
  private static TreeSet<Customer> customers = SerializationManager.loadCustomers();
  ```

- **Saving Data**:  
  All changes to the `Admin` and `Customer` objects are saved back to their respective `.dat` files on program exit.

---


#### **Data Inspection with DatFileInspector**  
The `DatFileInspector` utility helps inspect the serialized `.dat` files for debugging or verification purposes. It deserializes the objects stored in the files and prints their details in a structured format.  

**Steps to Use DatFileInspector**:  
1. Ensure the `.dat` files (`admin.dat` and `customers.dat`) are present in the application's working directory.  
2. Run the `DatFileInspector` program:  
   ```bash
   java DatFileInspector
   ```  
3. The program prints the contents of the files, including all nested objects like orders, menu items, and cart contents.  





#### **How to Run the Application**  
1. Compile and run the `ByteMeApp` class:
   ```bash
   javac ByteMeApp.java
   java ByteMeApp
   ```
2. Interact via the CLI to log in as an admin or customer.  
3. Trigger GUIs through menu options for specific features like browsing the menu or viewing pending orders.  

---

#### **How to Run DatFileInspector**  
1. Compile and run the `DatFileInspector` class:
   ```bash
   javac DatFileInspector.java
   java DatFileInspector
   ```
2. Inspect the contents of the serialized files (`admin.dat` and `customers.dat`).  

--- 



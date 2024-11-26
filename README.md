### README for Byte Me! Food Ordering System  

---

#### **Overview**  
Byte Me! is a food ordering system that provides a comprehensive platform for managing food menus, placing and tracking orders, generating reports, and handling customer accounts. The system utilizes both a **command-line interface (CLI)** and **graphical user interfaces (GUIs)** for ease of use. Data persistence is achieved through Java serialization, ensuring that all data is saved and restored across sessions.  

---

#### **Features**  

##### **Admin Functionality**  
- **Menu Management**:  
  - Add, update, browse, or remove menu items via CLI.  
  - Launch a GUI for menu browsing, which can be re-triggered from the CLI for updated views.  

- **Order Management**:  
  - View and manage pending orders via a GUI or CLI. The GUI includes a refresh button to update the displayed data without restarting the application.  

- **Report Generation**:  
  - Generate daily sales reports based on completed orders.  

##### **Customer Functionality**  
- **Menu Browsing**:  
  - Browse, search, filter, and sort menu items via CLI or GUI.  
- **Cart Management**:  
  - Add, modify, and remove items from the cart.  
  - Checkout and place orders.  
- **Order Tracking**:  
  - Track and cancel orders.  
- **Item Reviews**:  
  - Provide feedback and view item ratings.  

##### **Persistence**  
- Data is serialized into `.dat` files:
  - `admin.dat`: Contains the serialized `Admin` instance with all associated data (menu, orders, etc.).  
  - `customers.dat`: Contains a `TreeSet<Customer>` with all customer information and their associated data (order history, cart).  

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

---

#### **Assumptions**  
- The GUI for browsing the menu or viewing pending orders is triggered via CLI options.  
- **Menu GUI**:  
  - Once closed, the menu GUI must be re-launched via the CLI to view updated changes.  
- **Pending Orders GUI**:  
  - Includes a refresh button to update the view, or you can relaunch it via the CLI.  
- The system assumes logical user inputs where not explicitly validated (e.g., menu item names, quantities).  

---

#### **JUnit Testing**  
JUnit tests are implemented to ensure system reliability. Key scenarios include:  
1. **Ordering Out-of-Stock Items**:  
   - Verifies that out-of-stock items cannot be ordered, and an appropriate error message is displayed.  
2. **Invalid Login Attempts**:  
   - Simulates incorrect username or password inputs and verifies that access is denied with an appropriate error message.  

Each test is run with isolated data to ensure consistency.  

---

#### **File Management and Persistence**  
- **Serialized Data**:  
  The application uses Java's `ObjectOutputStream` and `ObjectInputStream` to handle data persistence. The `.dat` files are binary files and not human-readable.  

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

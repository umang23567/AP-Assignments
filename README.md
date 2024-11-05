# AP_Assignment3

## Project Overview

The project is a basic simulation of an ordering and inventory management system. It includes functionality for adding and managing food items, tracking orders, and handling special features such as VIP customer prioritization and feedback management. This system is intended for a single-session demonstration with hardcoded data for the purposes of the assignment.

The primary classes and features include:

- **FoodItem**: Represents items in the inventory with attributes like price, availability, and feedback.
- **Cart**: Allows users to add, remove, and view items, as well as calculate the total.
- **Order**: Tracks individual orders with details such as order status, VIP priority, and timestamps.
- **Admin and User Roles**: Admin can update inventory, while users can place orders and add feedback.

---

## Features

1. **Inventory Management**: Admins can add, modify, or remove items from the inventory.
2. **Ordering System**: Users can add items to their cart, view the total, and place orders.
3. **VIP Priority**: Orders are sorted to prioritize VIP customers.
4. **Feedback and Rating**: Users can provide feedback on food items, which contributes to an average rating.
5. **Order Tracking**: Each order has a unique ID, timestamp, and status that updates as it progresses.
6. **Session-Based Data**: The system uses hardcoded data, which resets after each session.

---

## Assumptions

This project operates under a few key assumptions. The assumptions have been made in certain cases which were not explicitly detailed in the assignment documentation or class comments.

### Assumption 1: User Input
The system assumes that users will provide logical and valid inputs for smooth operation without unexpected interruptions or errors.

### Assumption 2: Product Availability
- Items marked as **available** (availability set to `YES`) are assumed to have an **infinite supply**.
- Items marked as **unavailable** (availability set to `NO`) are treated as having a supply of `0`, making them unavailable for new orders until restocked.

### Assumption 3: Order Cancellation
Orders can be canceled only if they are in a **Pending** or **Preparing** state. Once orders reach the Dispatched, Delivered, or Completed stage, they cannot be canceled.

### Assumption 4: Payment and Refunds
- There is no Cash on Delivery option and payment is made during checkout, hence the total sales amount is recorded immediately when an order is placed.
- Refunds, if necessary, will later be managed manually by the admin.

### Assumption 5: Admin Controls
- Admin price updates for items **do not retroactively affect previous orders**.
- If the admin removes an item from the inventory, it will automatically be removed from all active user carts.

### Temporary Data Storage
This project uses hardcoded data for demonstration purposes, however, any changes to this data are temporary and are lost once the terminal session ends. 
Future development could include CSV-based or database storage for persistent data.

---

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd AP_Assignment3
   ```

2. Compile the Java files:
   ```bash
   javac *.java
   ```

3. Run the  application:
   ```bash
   java ByteMeApp
   ```

---

## Usage

This system includes the following roles and features:

### Admin Role

- **Add Item**: Add new items to the inventory with attributes such as price, availability, and category.
- **Update Item**: Modify the price, availability, or category of existing items.
- **Remove Item**: Remove items from the inventory, automatically clearing them from all user carts.
- **View Inventory**: See a list of all available items, their categories, prices, availability status, and ratings.

### User Role

- **Add Items to Cart**: Add items to a shopping cart with a specified quantity.
- **View Cart and Total**: View items in the cart and calculate the total price.
- **Place Order**: Confirm the items in the cart to place an order with details such as VIP status, order time, and address.
- **Track Order Status**: View and update order status through the predefined states.
- **Cancel Order**: Cancel an order if it is still in a Pending or Preparing state.
- **Leave Feedback**: Provide feedback and ratings for items, affecting the overall item rating.

---

## Classes Overview

- **Main**: The entry point for the application where users and admins can access different functionalities.
- **FoodItem**: Holds attributes like name, price, availability, and a list of feedback entries.
- **Cart**: Manages items selected by the user for purchase.
- **Order**: Represents an individual order with tracking information.
- **Admin/User Interfaces**: Separates admin and user functionality for easier management of roles.



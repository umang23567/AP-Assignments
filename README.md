# AP_Assignment3



## Assumptions

This project operates under a few key assumptions. The assumptions have been made in certain cases which were not explicitly detailed in the assignment documentation or class comments.

### Assumption 1: User Input
 It is assumed that the user will provide **logical and valid input**. This ensures the smooth operation of the system without causing unexpected interruptions or errors in the flow.

### Assumption 2: Product Availability
- The system assumes an **infinite supply** of items when their availability is set to `YES`. 
- When availability is set to `NO`, the system treats future supply as `0`.
- 
### Assumption 3: Order Cancellation
Order can be cancelled while in pending or preparing state, else cannot be cancelled.

### Assumption 4: Payment and Refunds
- There is no **Cash on Delivery (COD)** system. Thus, the total sales amount is recorded immediately after an order is placed by the user.
- Refunds for any canceled orders will be processed manually by the admin at a later time.

### Assumption 5: Admin Controls
- Any updates made by the admin regarding the prices of existing items will **not affect** the total amounts of previous orders. Similarly, changes in item availability will only apply to future orders.
- If an admin removes items from the inventory, those items will automatically be removed from the carts of all users.

Currently, the project uses hardcoded data for demonstration purposes. 
However, any changes to this data are temporary and are lost once the terminal session ends. 
To enable permanent storage of changes, implementing a CSV file would be a viable solution, which was not implemented due to time constrains and nature of the assignment.


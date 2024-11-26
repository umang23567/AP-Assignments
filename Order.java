import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Order implements Comparable<Order>,Serializable 
{
    private static final long serialVersionUID = 1L;

    private int orderId;
    private String status;                  // Pending,Preparing, Dispatched,Delivered,Completed, Cancelled,Denied, Refunded
    private double totalAmount;
    private LocalDateTime orderDateTime;
    private static int IDGen = 100000;          // ID generator for unique order IDs
    private Map<FoodItem, Integer> orderItems;
    private boolean VIP;
    private String specialRequest;
    private String address;

    public Order(double totalAmount, Map<FoodItem,Integer> cartItems, boolean vipStatus, LocalDateTime orderDateTime) 
    {
        this.orderId = IDGen++;
        this.totalAmount = totalAmount;
        this.status="Pending";
        this.orderItems=new HashMap<>(cartItems);
        this.VIP=vipStatus;
        this.orderDateTime = orderDateTime!=null ? orderDateTime : LocalDateTime.now();
        this.specialRequest=null;
        this.address=null;
    }

    public int getOrderId() 
    {
        return orderId;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public LocalDateTime getOrderDateTime() 
    {
        return orderDateTime;
    }

    public Map<FoodItem,Integer> orderItems()
    {
        return orderItems;
    }

    public boolean isVIP()
    {
        return VIP;
    }

    public double getTotal()
    {
        return totalAmount;
    }

    public String getSpecialRequest()
    {
        return specialRequest;
    }

    public void setAddress(String address)
    {
        this.address=address;
    }

    public String getAddress()
    {
        return address;
    }

    public void setSpecialRequest(String specialRequest)
    {
        this.specialRequest=specialRequest;
    }

    public String getItemsAsString() 
    {
        StringBuilder itemsString = new StringBuilder();
        for (Map.Entry<FoodItem, Integer> entry : orderItems.entrySet()) 
        {
            itemsString.append(entry.getKey().getName())
                       .append(" (x")
                       .append(entry.getValue())
                       .append("), ");
        }
        if (itemsString.length() > 0) 
        {
            itemsString.setLength(itemsString.length() - 2);
        }
        return itemsString.toString();
    }

    @Override
    public String toString() {
        return 
            "Order Id: " + orderId + "\n" +
            "Status: " + status + "\n" +
            "Total Amount: " + totalAmount + "\n" +
            "Order Time: " + orderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n" +
            "VIP: " + VIP + "\n" +
            "Special Request: " + (specialRequest != null && !specialRequest.isEmpty() ? specialRequest : "None") + "\n" +
            "Address: " + address + "\n" +
            "Order Items: " + orderItems.entrySet()
                                        .stream()
                                        .map(entry -> entry.getKey().getName() + " x" + entry.getValue())
                                        .collect(Collectors.joining(", ")) ;
    }


    @Override
    public int compareTo(Order other) 
    {
        if (this.isVIP() && !other.isVIP()) 
        {
            return -1; 
        }
        if (!this.isVIP() && other.isVIP()) 
        {
            return 1; 
        }

        return this.getOrderDateTime().compareTo(other.getOrderDateTime());
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) 
        {
            return false;
        }

        Order other=(Order) obj;
        return this.orderId == other.orderId && this.VIP == other.VIP;
    }   

    @Override
    public int hashCode() 
    {
        return Objects.hash(orderId,VIP);
    }
}

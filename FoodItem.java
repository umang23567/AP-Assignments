import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodItem {
    private String name;
    private double price;
    private String category;
    private boolean availability;
    private double rating;                 // each rating: 1-5
    private List<String> feedbacks;
    private int FeedbackCount;

    public FoodItem(String name, double price, String category) 
    {
        this.name = name;
        this.price = price;
        this.category = category;
        this.availability = true;
        this.rating=0.0;
        this.feedbacks=new ArrayList<>();
        this.FeedbackCount=0;
    }


    public String getName() 
    {
        return name;
    }

    public double getPrice() 
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price=price;
    }

    public String getCategory() 
    {
        return category;
    }

    public boolean isAvailable()
    {
        return availability;
    }

    public void changeAvailability(boolean availability)
    {
        this.availability=availability;
    }


    public double getRating()
    {
        return rating;
    }

    public List getFeedbacks()
    {
        return feedbacks;
    }

    public void addFeedback(int rating, String feedback)
    {
        this.FeedbackCount++;
        this.rating=(this.rating*(this.FeedbackCount-1)+rating)/this.FeedbackCount;
        feedbacks.add(feedback);
    }

    @Override
    public String toString() 
    {
        return "Item: " + 
        "Name=" + name + 
        ", Price=" + price +
        ", Category=" + category ;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodItem other = (FoodItem) obj;
        return name.equalsIgnoreCase(other.name);  
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase()); 
    }
}

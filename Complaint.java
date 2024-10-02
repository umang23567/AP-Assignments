import java.util.ArrayList;
import java.util.List;

public class Complaint {
    private static int idGen = 0;
    private String complaintID;
    private Student student;
    private String description;
    private boolean resolved;
    private static List<Complaint> complaints = new ArrayList<>();

    public Complaint(Student student, String description) 
    {
        this.complaintID = "100000" + String.valueOf(++idGen);
        this.student = student;
        this.description = description;
        this.resolved = false;
        complaints.add(this);
    }

    public String getComplaintID() 
    {
        return complaintID;
    }

    public Student getStudent() 
    {
        return student;
    }

    public String getDescription() 
    {
        return description;
    }

    public boolean isResolved() 
    {
        return resolved;
    }

    public void setResolved(boolean resolved) 
    {
        this.resolved = resolved;
    }

    public static boolean displayComplaints() 
    {
        boolean unresolved=false;
        for (Complaint complaint : complaints) 
        {   
            if (!complaint.isResolved()) 
            {
                System.out.println(complaint.getComplaintID() + " " + complaint.getStudent().getEmail() + " " + complaint.getDescription());
                unresolved=true;
            }
        }
        return unresolved;
    }
}

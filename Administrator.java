import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Administrator extends User 
{
    private List<Complaint> complaints=new ArrayList<>();
    private static Administrator instance;

    public Administrator(String email,String password) 
    {
        super(email, password);
    }

    public static Administrator getAdmin(String email,String password) 
    {
        if (instance==null) 
        {
            instance=new Administrator(email, password);
        }
        return instance;
    }

    public List<Complaint> getComplaints() 
    {
        return complaints;
    }

    Scanner scanner=new Scanner(System.in);

    public void viewAllCourses(List<Course> courses) {
        for (Course course : courses) {  
            System.out.println(
                course.getCourseCode() + " " +
                course.getCourseName() + " " + 
                course.getProfessor().getProfessorName() +
                " Credits:" + (course.getCredits()==0 ?"-" :course.getCredits()) + 
                " Class timings:" + (course.getClassTiming()==null ?"-" :course.getClassTiming())
                );
        }
    }

    public void manageCourseCatalog(List<Course> courses,List<Professor> professors)
    {
        System.out.println("1. Add Course");
        System.out.println("2. Remove Course");
        int action = scanner.nextInt();
        scanner.nextLine();  
        System.out.println("Enter course code:");
        String code=scanner.nextLine();
        if (action==1) 
        {
            AddInCourseCatalog(courses,code,professors);
        }
        else if (action==2)
        {
            RemoveInCourseCatalog(courses,code);
        }
    }

    public void AddInCourseCatalog(List<Course> courses,String code,List<Professor> professors)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter course name:");
        String courseName=sc.nextLine();
        System.out.println("Enter credits:");
        int credits=sc.nextInt();
        System.out.println("Enter semester:");
        int Semester=sc.nextInt();
        System.out.println("Enter professor name:");
        String ProfName=sc.nextLine();
        System.out.println("Enter professor ID:");
        String ProfID=sc.nextLine();

        Course newCourse=new Course(code,courseName,credits,null,Semester,ProfName,ProfID);
        courses.add(newCourse);
        
        boolean found=false;
        for (Professor prof: professors)
        {
            if (prof.getProfessorName().equals(ProfName))
            {
                newCourse.setProfessor(prof);
                break;
            }
        }
        
        if (!found)
        {
            System.out.println("Professor not found");
        }
        else
        {
            System.out.println("Course added: "+newCourse.getCourseCode());
        }
    }

    public void RemoveInCourseCatalog(List<Course> courses,String code)
    {   
        boolean found=false;
        Course foundCourse=null;
        for (Course course : courses) 
        {
            if (course.getCourseCode().equals(code)) 
            {
                foundCourse=course;
                found=true;
                break;
            }
        }
        if (!found)
        {
            System.out.println("Course not found");
        
        }
        else
        {
            courses.remove(foundCourse);
            System.out.println("Course removed: "+foundCourse.getCourseCode());
        }
        
    }

    public void viewStudentRecords(List<Student> students)
    {
        {
            for (Student student: students) 
            {   
                System.out.printf(student.getStudentID() + " Sem:" +student.getSemester() + " ");
                for (Map.Entry<Course,String> entry: student.getGrades().entrySet())
                { 
                    System.out.printf(entry.getKey().getCourseCode() + ":" + entry.getValue()==null ?"-" :entry.getValue() + " ");   
                }  
                System.out.printf("GPA:" + student.calculateGPA());    
                System.out.printf(" Phone:" + ((student.getPhone()==0) ?"-" :student.getPhone())); 
                System.out.println();      
            }
        }
    }

    public void ManageStudentRecords(List<Student> students)
    {
        System.out.println("Enter student email:");
        String email=scanner.nextLine();
        boolean found=false;
        for (Student student : students) 
        {
            if (student.getEmail().equals(email)) 
            {
                found=true;
                System.out.println("1. Update Grade");
                System.out.println("2. Update Phone number");
                int action=scanner.nextInt();
                scanner.nextLine();  
                switch (action)
                {   
                    case 1:
                        System.out.println("Enter course code:");
                        String code=scanner.nextLine();
                        boolean exist=false;
                        for (Course course: student.getCourses())
                        {
                            if (course.getCourseName().equals(code))
                            {   
                                System.out.println("Enter new grade");
                                String gd=scanner.nextLine();
                                exist=true;
                                boolean added=false;

                                for (Map.Entry<Course, String> entry : student.getGrades().entrySet()) 
                                {
                                    if (entry.getKey().getCourseCode().equals(code))
                                    {
                                        student.setGrade(course,gd);
                                        added=true;
                                        break;
                                    }
                                }                                              
                                if (!added)
                                {
                                    student.addGrade(course,gd);
                                }
                                break;
                            }
                        }
                        if (!exist)
                        {
                            System.out.println("Course not found");
                        }
                        break;
                    case 2:
                        System.out.println("Enter phone number:");
                        int phn=scanner.nextInt();
                        student.setPhone(phn);
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            }
        }
        if (!found)
        {
            System.out.println("Student not found");
        }
    }

    public void assignProfessors(List<Course> courses, List<Professor> professors)
    {
        Course foundCourse=null;
        Professor foundProfessor=null;
        System.out.println("Enter course code:");
        String code=scanner.nextLine();
        boolean found=false;
        for (Course course: courses) 
        {
            if (course.getCourseCode().equals(code)) {
                foundCourse=course;
                break;
            }
        }
        if (foundCourse!=null) 
        {
            System.out.println("Enter professor email:");
            String profEmail=scanner.nextLine();
            for (Professor professor: professors) 
            {
                if (professor.getEmail().equals(profEmail)) 
                {
                    foundProfessor=professor;
                    found=true;
                    break;
                }
            }
        }
        else
        {
            System.out.println("Course not found");
        }

        if (!found)
        {   
            System.out.println("Professor not found");
        }
        else
        {
            System.out.println("Professor " + foundProfessor.getEmail() + " assigned to " + foundCourse.getCourseCode() + " course");
            foundCourse.setProfessor(foundProfessor);
        }
    }

    public void handleComplaint(Administrator admin) 
    {   
        boolean unresolved=Complaint.displayComplaints(); 
        Complaint foundComplaint=null;
                    
        if (unresolved)
        {   
            boolean found=false;
            System.out.println("Enter complaint ID to handle:");
            String complaintID = scanner.nextLine();
            for (Complaint complaint : admin.getComplaints()) 
            {
                if (complaint.getComplaintID().equals(complaintID)) 
                {
                    System.out.println("1. Mark as Resolved");
                    int complaintAction=scanner.nextInt();
                    scanner.nextLine();  
                    if (complaintAction==1) 
                    {
                        foundComplaint=complaint;
                        found=true;
                    }
                    else
                    {
                        System.out.println("Invalid choice. Try again.");
                    }
                    break;
                }
            }
            if (!found)
            {
                System.out.println("Invalid ID");
            }
            else
            {
                foundComplaint.setResolved(true);
                System.out.println("Complaint resolved: " + foundComplaint.getDescription());
            }
        }
        else
        {
            System.out.println("No pending complaints");
        }  
    } 

    public void receiveComplaint(Complaint complaint) 
    {
        complaints.add(complaint);
        System.out.println("Received complaint: " + complaint.getDescription());
    }

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Student extends User 
{
    private String studentID;
    private int sem;
    private int phone;
    private List<Course> registeredCourses = new ArrayList<>();
    private Map<Course, String> courseGrades = new HashMap<>();

    public Student(String email, String password, String studentID, int sem) 
    {
        super(email, password);
        this.studentID = studentID;
        this.sem=sem;
    }

    public int getSemester()
    {
        return sem;
    }
    public String getStudentID()
    {
        return studentID;
    }
    public List<Course> getCourses()
    {
        return registeredCourses;
    }
    public Map<Course,String> getGrades()
    {
        return courseGrades;
    }
    public int getPhone()
    {
        return phone;
    }
    public void setPhone(int phn)
    {
        phone=phn;
    }

    Scanner scanner = new Scanner(System.in);

    public void viewAvailableCourses(List<Course> courses) 
    {
        System.out.println("Courses available for this semester:");
        for (Course course : courses) 
        {
            if (course.getSemester()==sem)
            System.out.println(
                course.getCourseCode() + " " + course.getCourseName() +
                " " + course.getProfessor() +
                " Credits:" + (course.getCredits()==0 ?"-" :course.getCredits()) + 
                " Class timings:" + (course.getClassTiming()==null ?"-" :course.getClassTiming()) +
                " Syllabus:" + (course.getSyllabus()==null ?"-" : course.getSyllabus())
                );
        }
    }

    public void registerForCourse(List<Course> courses) 
    {
        System.out.println("Enter course code to register:");
        String code = scanner.nextLine();
        boolean found=false;
        for (Course course : courses) 
        {
            if (course.getCourseCode().equals(code)) 
            {
                found=true;
                if (!registeredCourses.contains(course)) 
                {
                    if (course.getSemester()==sem)
                    {
                        registeredCourses.add(course);
                        course.addStudent(this);
                        courseGrades.put(course,"");
                        System.out.println("Registered for " + course.getCourseName());
                    }
                    else
                    {
                        System.out.println("Course not avaialable for this semester");
                    }
                } 
                else 
                {
                    System.out.println("Already registered for " + course.getCourseName());
                    break;
                }       
            }
        }
        if (!found)
        {
            System.out.println("Course not found");
        }
    }

    public void registerForCourse(Course course, List<Course> courses) 
    {   
        registeredCourses.add(course);
        course.addStudent(this);
    }

    public void viewSchedule() 
    {
        System.out.println("Your schedule:");
        for (Course course : registeredCourses) 
        {
            System.out.println(
                course.getCourseCode() + " " + course.getCourseName() + 
            " " + course.getProfessor() + " " + course.getClassTiming() 
            );
        }
    }

    public void viewGrades() 
    {
        System.out.println("Your grades:");
        for (Map.Entry<Course, String> entry : courseGrades.entrySet()) 
        {
            System.out.println(entry.getKey().getCourseCode() + ":" + entry.getValue());
            
        }
        System.out.println("Current GPA:" + calculateGPA());
    }

    public void setGrade(Course course, String gd) 
    {
        for (Map.Entry<Course, String> entry : courseGrades.entrySet()) 
        {
            if (entry.getKey().getCourseCode().equals(course.getCourseCode()))
            {
                courseGrades.put(entry.getKey(),gd);
            }
            
        }
    }

    public void addGrade(Course course, String grade) {
        courseGrades.put(course, grade);
    }

    public double calculateGPA() 
    {
        double totalPoints=0.0;
        int totalCourses=courseGrades.size();
        for (String grade: courseGrades.values()) 
        {
            totalPoints+=points(grade);
        }
        return totalCourses==0 ?0.0 :totalPoints/totalCourses;
    }

    public double points(String grade)
    {   
        switch(grade)
        {
            case "A":
                return 10.0;
            case "A-":
                return 9.0;
            case "B":
                return 8.0;
            case "B-":
                return 7.0;
            case "C":
                return 6.0;
            case "C-":
                return 5.0;
            case "D":
                return 4.0;
            default:
                return 0.0;
        }
    }

    public void dropCourse(List<Course> courses) 
    {
        System.out.println("Enter course code to drop:");
        String code = scanner.nextLine();
        boolean found=false;
        for (Course course : courses) 
        {
            if (course.getCourseCode().equals(code)) 
            {
                found=true;
                if (registeredCourses.contains(course)) 
                {
                    registeredCourses.remove(course);
                    course.removeStudent(this);
                    courseGrades.remove(course,courseGrades.get(course));
                    System.out.println("Dropped " + course.getCourseName());
                } 
                else 
                {
                    System.out.println("Not registered for " + course.getCourseName());
                }
                break;
            }           
        }
        if (!found)
        {
            System.out.println("Course not found");
        }
    }

    public void submitComplaint() 
    {
        System.out.println("Enter complaint description:");
        String description=scanner.nextLine();
        Complaint complaint=new Complaint(this, description);
        Administrator admin=Administrator.getAdmin("admin@example.com", "adminpass");
        admin.receiveComplaint(complaint);
    }
    
}

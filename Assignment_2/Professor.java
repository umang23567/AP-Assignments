import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Professor extends User 
{
    private String professorID;
    private String professorName;
    private List<Course> coursesTaught = new ArrayList<>();

    public Professor(String email, String password, String professorID, String professorName) {
        super(email, password);
        this.professorID = professorID;
        this.professorName = professorName;
    }

    Scanner scanner=new Scanner(System.in);

    public void setProfessorID(String professorID) 
    {
        this.professorID = professorID;
    }

    public void setProfessorName(String professorName) 
    {
        this.professorName = professorName;
    }

    public String getProfessorName()
    {
        return professorName;
    }
    public String getProfessorID()
    {
        return professorID;
    }

    public List<Course> getcoursesTaught()
    {
        return coursesTaught;
    }

    public void viewAllCourses(List<Course> courses) 
    {
        System.out.println("Your Courses:");
        for (Course course: coursesTaught) 
        {
            System.out.println(
                                course.getCourseCode() + " " + course.getCourseName() +
                                " Credits:" + (course.getCredits()==0 ?"-" :course.getCredits()) + 
                                " Class timings:" + (course.getClassTiming()==null ?"-" : course.getClassTiming()) +
                                " Syllabus:" + (course.getSyllabus()==null ?"-" :course.getSyllabus()) + 
                                " Office hours:" + (course.getOfficeHours()==null ?"-" : course.getOfficeHours()) +
                                " Enrollment Limits:" + course.getEnrollmentLimits() 
                            );
        }
    }

    public void manageCourseDetails(Professor professor, List<Course> courses) 
    {   
        System.out.println("Enter course code to manage:");
        String code=scanner.nextLine();
        boolean found=false;
        boolean match=false;
        Course foundCourse=null;
        for (Course course: courses) 
        {
            if (course.getCourseCode().equals(code)) 
            {
                found=true;
                if (course.getProfessor()==professor)
                {       
                    foundCourse=course;
                    match=true;
                    break;
                }
            }
        }
                        
        if (!found)
        {
            System.out.println("Course not found");
        }
        else if (!match)
        {
            System.out.println("You do not teach this course");
        }
        else if (match)
        {
            System.out.println("1. Update Syllabus");
            System.out.println("2. Update Timings");
            System.out.println("3. Update Enrollment limits");
            System.out.println("4. Update Office Hours");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline
            System.out.println("Enter the data to be updated:");
            String new_data = scanner.nextLine();

            switch (choice) 
            {        
                case 1 -> foundCourse.setSyllabus(new_data);          
                case 2 -> foundCourse.setClassTiming(new_data);      
                case 3 -> foundCourse.setEnrollmentLimits(Integer.parseInt(new_data)); 
                case 4 -> foundCourse.setOfficeHours(new_data);
                default -> System.out.println("Invalid choice. Try again.");
            }
            
            if (choice>0 && choice<5) 
            {
                System.out.println("Updated course details for "+foundCourse.getCourseCode());
            }
        }
    }

    public void viewEnrolledStudents(Professor professor, List<Course> courses) 
    {
        System.out.println("Enter course code to view enrolled students:");
        String code = scanner.nextLine();
        boolean found=false;
        boolean match=false;
        Course foundCourse=null;
        for (Course course: courses) 
        {
            if (course.getCourseCode().equals(code)) 
            {
                found=true;
                if (course.getProfessor() == professor)
                {   
                    foundCourse=course;
                    match=true;
                    break;
                }
            }
        }
        if (!found)
        {
            System.out.println("Course not found");
        }
        else if (!match)
        {
            System.out.println("You do not teach this course");
        }
        else if (match) 
        {

            if (foundCourse.getProfessor().getProfessorName().equals(professorName))
            {   
                List<Student> students=foundCourse.getEnrolledStudents();
                
                if (students.isEmpty()) 
                {
                    System.out.println("No students enrolled.");
                } 
                else 
                {   
                    System.out.println("Enrolled students for "+foundCourse.getCourseCode() + ":");
                    for (Student student : students) 
                    {
                        System.out.println(
                            student.getEmail() +
                            " Phone:" + (student.getPhone()==0 ?"-" :student.getPhone()) +
                            " GPA:" + student.calculateGPA());
                    }
                }
            }
            else
            {
                System.out.println("You do not teach this course");
            }
        }
    }

    public void viewFeedbackForCourses() 
    {
        for (Course course : coursesTaught) 
        {
            System.out.println("Feedback for course: " + course.getCourseName());
    
            List<Feedback<?>> feedbackList = course.getFeedbackList();
            if (feedbackList.isEmpty()) 
            {
                System.out.println("No feedback available for this course.");
            } 
            else 
            {
                for (Feedback<?> feedback : feedbackList) 
                {
                    if (feedback.getFeedback() instanceof Integer) 
                    {
                        System.out.println("Rating: " + feedback.getFeedback());
                    }
                    else if (feedback.getFeedback() instanceof String) 
                    {
                        System.out.println("Remark: " + feedback.getFeedback());
                    }
                }
            }
        }
    }
    
    public void viewTAs(List<TeachingAssistant> TAs) 
    {   
        boolean atleast=false;
        System.out.println("Teaching Assistants:");
          
            
        for (TeachingAssistant TA : TAs) 
        {
            if (TA.getProfessor()==this)
            {
                System.out.println("TA ID: " + TA.getTAid() );
                atleast=true;
            }
        }

        if (!atleast)
        {
            System.out.println("No Teaching Assistants assigned as of yet.");
        }
    }
    
}

import java.util.List;

// TA is also a student
public class TeachingAssistant extends Student 
{ 
    private Course course;                                      // TAship for only one course allowed
    private List<Student> enrolledStudents;
    private Professor professor;

    public TeachingAssistant(String email, String password, String ID, int sem, Course course) 
    {
        super(email, password,ID, sem);
        this.course=course;
        this.enrolledStudents=course.getEnrolledStudents();
        this.professor=course.getProfessor();
    }

    public Professor getProfessor()
    {
        return professor;
    }

    public String getTAid()
    {
        return getStudentID();
    }

    public void viewStudentGrades() 
    {   
        System.out.println("Grades for course: " + course.getCourseName());
        for (Student student: enrolledStudents)
        {
            if (!student.getGrades().get(course).isEmpty())
            System.out.println(student.getStudentID() + ":" + student.getGrades().get(course));
            else
            System.out.println(student.getStudentID() + ":- " );
        }
    }

    public void updateStudentGrade() 
    {
        Student studentFound=null;
        System.out.println("Enter Student ID:");
        String StudentID=scanner.nextLine();
        for (Student student: enrolledStudents)
        {
            if (student.getStudentID().equals(StudentID) && student.getCourses().contains(course));
            {
                studentFound=student;
                break;
            }
        }
        if (studentFound!=null)
        {   
            System.out.println("Enter new grade:");
            String grade=scanner.nextLine();
            studentFound.setGrade(course,grade);
        }
        else
        {
            System.out.println("Student not found");
        }
        
    }

}

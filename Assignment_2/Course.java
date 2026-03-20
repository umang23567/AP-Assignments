import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course 
{
    private String courseCode;
    private String courseName;
    private int credits;
    private String classTiming;
    private List<Student> enrolledStudents=new ArrayList<>();
    private int Semester;
    private Professor professor;
    private String professorName;
    private String professorID;
    private String Syllabus;
    private String OfficeHours;
    private int EnrollmentLimits;
    private LocalDate dropDeadline;
    private List<Feedback<?>> feedbackList = new ArrayList<>();

    public Course(String courseCode, String courseName, int credits, String classTiming, int Semester, String professorName ,String professorID, int EnrollmentLimits) 
    {
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.credits=credits;
        this.classTiming=classTiming;
        this.Semester=Semester;
        this.professorName=professorName;
        this.professorID=professorID;
        this.EnrollmentLimits=EnrollmentLimits;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }

    public String getSyllabus()
    {
        return Syllabus;
    }

    public int getSemester() 
    {
        return Semester;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public int getCredits() 
    {
        return credits;
    }

    public String getClassTiming() 
    {
        return classTiming;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getOfficeHours()
    {
        return OfficeHours;
    }
    public int getEnrollmentLimits()
    {
        return EnrollmentLimits;
    }

    public List<Student> getEnrolledStudents() 
    {
        return enrolledStudents;
    }

    public LocalDate getDropDeadline()
    {
        return dropDeadline;
    }

    public void setProfessor(Professor professor) 
    {
        this.professor=professor;
        professor.getcoursesTaught().add(this);
    }

    public void setCourseName(String courseName) 
    {
        this.courseName=courseName;
    }

    public void setOfficeHours(String OfficeHours)
    {
        this.OfficeHours=OfficeHours;
    }

    public void setEnrollmentLimits(int EnrollmentLimits)
    {
        this.EnrollmentLimits=EnrollmentLimits;
    }

    public void setCredits(int credits) 
    {
        this.credits=credits;
    }

    public void setSemester(int Semester) 
    {
        this.Semester=Semester;
    }

    public void setCourseCode(String courseCode) 
    {
        this.courseCode=courseCode;
    }

    public void setSyllabus(String Syllabus) 
    {
        this.Syllabus=Syllabus;
    }

    public void setClassTiming(String classTiming) 
    {
        this.classTiming=classTiming;
    }

    public void setDropDeadline(LocalDate dropDeadline)
    {
        this.dropDeadline=dropDeadline;
    }


    public void addStudent(Student student) 
    {
        if (!enrolledStudents.contains(student)) 
        {
            enrolledStudents.add(student);
        }
    }

    public void removeStudent(Student student) 
    {
        enrolledStudents.remove(student);
    }

    public void addFeedback(Feedback<?> feedback) 
    {
        feedbackList.add(feedback);
    }

    public List<Feedback<?>> getFeedbackList() 
    {
        return feedbackList;
    }
}

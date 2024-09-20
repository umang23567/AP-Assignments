import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main 
{
    private static List<Course> courses=new ArrayList<>();
    private static List<Student> students=new ArrayList<>();
    private static List<Professor> professors=new ArrayList<>();
    private static Administrator admin=new Administrator("admin@mail.com", "adminpass");

    
    public static void main(String[] args) 
    {

        // Sample data 

        Course course1=new Course("CS101", "Intro to CS", 4, "Mon-Wed 10 AM",1,"prof1","P001");
        Course course2=new Course("CS102", "Data Structures", 4, "Tue-Thu 2 PM",2,"prof2","P002");
        Course course3=new Course("CS201", "Algorithms", 4, "Mon-Wed 1 PM", 1, "prof1", "P001");
        Course course4=new Course("CS202", "Operating Systems", 4, "Tue-Thu 10 AM", 2, "prof3", "P003");
        Course course5=new Course("CS301", "Database Systems", 2, "Mon-Wed 3 PM", 3, "prof3", "P003");
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);

        Student student1=new Student("student1@mail.com","pass123","S001",1);
        Student student2=new Student("student2@mail.com","pass456","S002",2);
        Student student3=new Student("student3@mail.com","pass789","S003",3);
        students.add(student1);
        students.add(student2);
        students.add(student3);

        Professor prof1=new Professor("prof1@mail.com","profpass1","P001","prof1");
        Professor prof2=new Professor("prof2@mail.com","profpass2","P002","prof2");
        Professor prof3=new Professor("prof3@mail.com","profpass3","P003","prof3");
        professors.add(prof1);
        professors.add(prof2);
        professors.add(prof3);


        course1.setProfessor(prof1);
        course2.setProfessor(prof2);
        course3.setProfessor(prof1);
        course4.setProfessor(prof3);
        course5.setProfessor(prof3);

        student1.registerForCourse(course1,courses);
        student2.registerForCourse(course2,courses);
        student3.registerForCourse(course4,courses);
        student3.registerForCourse(course5,courses);

        student1.addGrade(course1,"A");
        student2.addGrade(course2,"B");
        student3.addGrade(course4,"C-");
        student3.addGrade(course5,"C");


        // main code

        Scanner scanner=new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the University Course Registration System!");
        while (running) 
        {
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Professor");
            System.out.println("3. Login as Administrator");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) 
            {
                case 1 ->
                {
                    loginStudent(scanner);
                    System.out.println("Welcome Student!");
                }
                case 2 ->
                {
                    loginProfessor(scanner);
                    System.out.println("Welcome Professor!");
                }
                case 3 ->
                {
                    loginAdministrator(scanner);
                    System.out.println("Welcome Admin!");
                }
                case 4 ->
                {
                    running=false;
                    System.out.println("Exiting application...");
                }
                default ->
                {
                    System.out.println("Invalid choice! Try again.");
                    break;
                }
            }
        }
    }

    private static void loginStudent(Scanner scanner) 
    {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (Student student : students) 
        {
            if (student.login(email, password)) 
            {
                studentMode(scanner, student);
                return;
            }
        }
        System.out.println("Invalid email or password.");
    }

    private static void studentMode(Scanner scanner,Student student) 
    {
        boolean loggedIn = true;

        while (loggedIn) 
        {
            System.out.println("\nStudent Mode:");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for Courses");
            System.out.println("3. View Schedule");
            System.out.println("4. Track Academic Progress");
            System.out.println("5. Drop Courses");
            System.out.println("6. Submit Complaints");
            System.out.println("7. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) 
            {
                case 1 -> student.viewAvailableCourses(courses);
                case 2 -> student.registerForCourse(courses);
                case 3 -> student.viewSchedule();        
                case 4 -> student.viewGrades(); 
                case 5 -> student.dropCourse(courses);
                case 6 -> student.submitComplaint();
                case 7 ->
                {
                    student.logout();
                    loggedIn=false;
                }
                default -> System.out.println("Invalid option!"); 
            }
        }
    }

    private static void loginProfessor(Scanner scanner) 
    {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (Professor professor : professors) 
        {
            if (professor.login(email, password)) 
            {
                professorMode(scanner, professor);
                return;
            }
        }
        System.out.println("Invalid email or password.");
    }

    private static void professorMode(Scanner scanner,Professor professor) 
    {
        boolean loggedIn = true;

        while (loggedIn) 
        {
            System.out.println("\nProfessor Mode:");
            System.out.println("1. View Courses");
            System.out.println("2. Manage Courses");
            System.out.println("3. View Enrolled Students");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) 
            {
                case 1 -> professor.viewAllCourses(courses);
                case 2 -> professor.manageCourseDetails(professor, courses);
                case 3 -> professor.viewEnrolledStudents(professor, courses);
                case 4 ->
                {
                    professor.logout();
                    loggedIn = false;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void loginAdministrator(Scanner scanner) 
    {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (admin.login(email, password)) 
        {
            adminMode(scanner, admin);
        } 
        else 
        {
            System.out.println("Invalid email or password.");
        }
    }

    private static void adminMode(Scanner scanner,Administrator admin) 
    {
        boolean loggedIn = true;

        while (loggedIn) 
        {
            System.out.println("\nAdministrator Mode:");
            System.out.println("1. View Courses");
            System.out.println("2. Manage Course Catalog");
            System.out.println("3. View Student Records");
            System.out.println("4. Manage Student Records");
            System.out.println("5. Assign Professors to Courses");
            System.out.println("6. Handle Complaints");
            System.out.println("7. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) 
            {
                case 1 -> admin.viewAllCourses(courses);
                case 2 -> admin.manageCourseCatalog(courses,professors);
                case 3 -> admin.viewStudentRecords(students);           
                case 4 -> admin.ManageStudentRecords(students);
                case 5 -> admin.assignProfessors(courses,professors);
                case 6 -> admin.handleComplaint(admin);
                case 7 ->
                {
                    admin.logout();
                    loggedIn = false;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

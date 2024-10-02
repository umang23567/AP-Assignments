Generic Programming:
In this project, generic programming is used to create flexible, reusable components that work with any data type. 
For example, we implemented a generic Feedback<T> class, where T can represent different types of feedback (e.g., numeric ratings or textual comments). 
This allows us to store and handle different feedback types without duplicating code for each type. 

Object Classes:
The project follows the principles of object-oriented programming (OOP) by organizing the system into classes, each representing a real-world entity. 
Key object classes include:
1) Student, which holds student-related information like courses they are registered for and their grades.
2) Course, which manages course-specific data, such as enrolled students, course limits, and deadlines.
3) Professor, representing the professors who manage courses.
4) Administrator, responsible for handling student complaints.
5) Teaching Assistant (TA), a class extending Student but with additional features related to managing grades.
(the TA class extends the Student class, inheriting its properties and methods while adding functionality specific to a TA’s role)

Exception Handling:
To ensure that errors are handled gracefully and the system behaves predictably, we implemented custom exception handling. 
Some custom exceptions used in the system are:
1) CourseFullException: Thrown when a student attempts to register for a course that has reached its enrollment limit.
2) DropDeadlinePassedException: Thrown when a student tries to drop a course after the drop deadline has passed.
3) InvalidLoginException: Thrown when incorrect credentials are provided during login.

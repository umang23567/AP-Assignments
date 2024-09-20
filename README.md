How to Run the Code:

1. Save all your Java files in a single folder.
2. Open the terminal and navigate to that folder.
3. Compile the files by typing: javac *.java
4. Run the program by typing: java Main


Note: The Main class already contains predefined data, which can be modified if needed for evaluating the functionality of the code.


Limitations: Due to tight deadlines, time constraints hindered the development of a more comprehensive and realistic Course Management System:

1. Lack of Data Persistence:
Limitation: Currently, the project seems to rely on in-memory data (like List<Course> and List<Student>), which means that all data will be lost once the program terminates. There is no way to save or load course or student data from a file or database.
Improvement: We could implement file I/O (e.g., reading/writing CSV files) or integrate a database to store courses, students, and professor details persistently.

2. The current system does not support managing courses, grades, or student records across different semesters, limiting its usability for ongoing academic programs.
   
3. Scalability Concerns:
Limitation: The current design may face performance issues if scaled up. For example, the project uses simple for loops to search through lists of courses and students. As the number of courses or students grows, these linear searches will become less efficient.
Improvement: We could consider using more efficient data structures, like hash maps or databases, to store and retrieve courses and students in constant or logarithmic time.

4. Hard-Coded Inputs:
Limitation: Inputs such as professor names and course codes are manually compared using strings, which can lead to issues if users provide inputs in different formats, such as variations in casing or spacing.
Improvement: Instead of relying on string comparison, we could consider using unique identifiers for each entity to ensure more reliable lookups and avoid errors caused by formatting discrepancies.

My Code Structure:
Main Class: The entry point of the application, containing pre-fed data for testing and demonstration.
User Classes:
User: An abstract base class defining common attributes (email, password) and methods.
Student: Inherits from User and includes attributes specific to students, such as GPA and enrolled courses.
Professor: Inherits from User and manages courses taught, enrolled students, and course details.
Administrator: Inherits from User and oversees the overall system functionality.
Course Class: Represents a course, including attributes like course code, name, credits, timings, syllabus, office hours, and a list of enrolled students.
Complaint Class: Manages complaints submitted by students to the administrator.

The OOP concepts used in my project are as follows:

1. Encapsulation: We ensure that the data members of our classes, such as `Student`, `Professor`, `Course`, and `Complaint`, are kept private. We provide public methods, specifically getters and setters, to access and modify these members. This approach allows us to maintain controlled access to the data, ensuring that it is modified in a predictable manner.

2. Abstraction: We utilize abstract classes and interfaces in our design, particularly with the `User` superclass. This enables us to define common behaviors for different user types, such as students, professors, and administrators. By doing so, we simplify the interaction with various functionalities across user roles, allowing us to focus on higher-level operations without getting bogged down in specific details.

3. Inheritance: Our design employs inheritance, where classes like `Professor` and `Administrator` inherit from the `User` class. This allows us to share properties and methods among these classes, reducing code duplication and enhancing maintainability. By structuring our code this way, we promote a cleaner and more organized codebase.

4. Polymorphism: We implement polymorphism in our project by allowing methods to be overridden in subclasses, such as those found in the `Administrator` and `Professor` classes. This feature enables us to provide different implementations based on the specific object type while still maintaining a consistent interface, making our system flexible and easier to extend.

5. Composition: We use composition in our design, where objects are composed of other objects. For instance, a `Course` object contains a list of `Student` objects. This approach enables us to create a modular design that accurately represents real-world relationships and interactions, which is crucial for our system's functionality.

6. Association: We establish associations between classes through methods and attributes. For example, a `Student` is associated with `Course` objects via enrollment. This relationship showcases how different entities interact within our system, allowing us to model the complexities of our application more effectively.


NOTE: UML diagrams are inside the submitted zip folder.


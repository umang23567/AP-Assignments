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


The OOP concepts used in my project are as follows:
1. Encapsulation:
Data members of classes (e.g., Student, Professor, Course, Complaint) are kept private, and public methods (getters and setters) are provided to access and modify them, ensuring controlled access.
2. Abstraction:
Abstract classes or interfaces are used (e.g., a User superclass) to define common behaviors for different user types (students, professors, administrators), allowing for a simplified interaction with various user functionalities.
3. Inheritance:
Classes like Professor and Administrator inherit from the User class, allowing them to utilize shared properties and methods, reducing code duplication and enhancing maintainability.
4. Polymorphism:
Methods can be overridden in subclasses (e.g., methods in Administrator and Professor classes), allowing different implementations based on the object type while maintaining a consistent interface.
5. Composition:
Objects are composed of other objects (e.g., a Course object contains a list of Student objects), enabling a modular design that represents real-world relationships effectively.
6. Association:
Classes are related through methods and attributes, such as Student being associated with Course objects via enrollment, showcasing how different entities interact within the system.

These concepts collectively enhance the design, functionality, and maintainability of the course management system.

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


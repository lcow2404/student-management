# student-management
Overview
This project is a Spring Boot web application for managing student records. It allows users to:

View a list of students.
Add a new student.
Delete a student by their ID.
Export all students as a JSON file.
The application uses Thymeleaf for front-end rendering and validates student data. Data is stored temporarily in-memory using a List<Student> (no persistent storage such as a database is used in this assignment). In future iterations, persistent storage will be added.


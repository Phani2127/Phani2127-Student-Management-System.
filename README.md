# Student Management System using Java and MySQL

A simple console-based **Student Management System (SMS)** developed using **Core Java**, **JDBC**, and **MySQL**. This project allows users to perform CRUD operations on student records stored in a MySQL database.

---

## Features

* Add a new student
* View all students
* Update a student's grade and email
* Delete a student
* Search student by ID
* Search student by Name
* Count total number of students
* Exit the application

---

## Technologies Used

* Java (JDK 8 or above)
* JDBC (Java Database Connectivity)
* MySQL Database
* Eclipse/IntelliJ IDEA (optional for development)

---

## Database Details

* **Database Name:** `studentdb`
* **Table Name:** `student`

### Table Structure (MySQL)

```sql
CREATE TABLE student (
  sid INT PRIMARY KEY,
  sname VARCHAR(100),
  age INT,
  sgrade VARCHAR(10),
  email VARCHAR(100)
);
```

---

## How to Run

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/StudentManagementSystem-Java.git
   cd StudentManagementSystem-Java
   ```

2. **Set up MySQL Database**

   * Create the database and table using the above SQL.
   * Update your database credentials in the `SMS.java` file:

     ```java
     private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
     private static final String USER = "root";
     private static final String PASS = "your_password";
     ```

3. **Compile and Run**

   * Using terminal:

     ```bash
     javac SMS.java
     java SMS
     ```
   * Or run through your IDE (Eclipse, IntelliJ, etc.)

---

## Sample Console Menu

```
Student Management System
1. Add Student
2. View All Students
3. Update Student
4. Delete Student
5. Search Students by ID
6. Search Students by Name
7. Count Total Students
8. Exit
```

---

## Future Enhancements

* Add email validation or age range checks
* Implement file logging or export to CSV
* Add GUI using Swing or JavaFX
* Implement connection pooling

---

## License

This project is open-source and free to use.


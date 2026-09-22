# Smart Campus Student Management System

**Name:** Kyarisiima Angella  
**Reg No:** 2444/G  
**Course:** DCS Year 2 - Object Oriented Programming (Java)  
**Project:** Smart Campus Student Management and Academic Information System

This project is for the Java coursework based on `cs OOP.pdf`. It manages students, lecturers, departments, programs, courses, attendance, marks and results.

## How to Run
```bash
cd smart-campus-system
javac Main.java
java Main
```
Then open: http://localhost:8080

You will see a login page.

## Login Accounts
- Admin: `admin` / `admin123` -> Admin dashboard (manage students, courses, reports)
- Lecturer: `lecturer1` / `lecturer123` -> Lecturer dashboard (my courses, attendance, marks)
- Student: `angella` / `student123` -> Student dashboard (courses, attendance, results, transcript)

Each user sees only their own dashboard. If you try to open another dashboard it shows access denied.

## What the System Does
- Add students, departments, programs and courses
- Assign lecturers to courses
- Enroll students to courses
- Record attendance (PRESENT/ABSENT)
- Enter coursework (40) and exam (60) marks and it calculates total, grade (A,B,C,D,F) and grade point automatically
- Calculate GPA and show class (First Class etc)
- Show transcript for each student
- Show reports like number of students per department

## Sample Data
- Departments: Computer Science (CSC), Management (MGT)
- Courses: CSC211 OOP (4 credits), CSC212 Data Comm (3), CSC213 Database (3)
- Lecturer: Dr. Namatovu - teaches CSC211 and CSC212
- Students: Nalwoga Grace (2025/DCS/DAY/0088) and Kyarisiima Angella (2444/G)
- Results: Grace has 85 (A) and 70 (B), GPA 4.57

## Files
- `Main.java` - all classes and server in one file
- `cs OOP.pdf` - coursework document

GitHub: https://github.com/Angel-Kyarisiima/KYARISIIMA-ANGELLA-2444-G/tree/main/smart-campus-system

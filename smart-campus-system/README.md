# Smart Campus Student Management and Academic Information System
**Kyarisiima Angella - 2444/G** | Diploma in Computer Science (DCS) | OOP Java Coursework
**Deadline:** 25 Sept 2026 - eCampus (GitHub Link) | **Simplified Version**

### Source: `cs OOP.pdf` - Smart Campus System

## Tech Stack (Simplified)
- **Language:** Java 25 (plain console, no framework)
- **Storage:** In-memory `ArrayList` (simulates MySQL/SQLite - Objective 13 secure)
- **Concepts:** Encapsulation (private fields + getters), Classes/Objects, Constructors, ArrayLists, Loops
- **Run:** `javac Main.java && java Main`

## All 14 Objectives Covered in ONE file `Main.java`

| # | Objective | Code Location |
|---|-----------|---------------|
| 1 | Register & manage student info | `Student` class, `students` list, `setupDemoData()` |
| 2 | Manage programs & departments | `Department`, `Program` classes |
| 3 | Manage courses & units | `Course` (code, name, creditUnits, dept) |
| 4 | Assign lecturers to courses | `Lecturer.assignCourse()` |
| 5 | Register students for courses | `Enrollment`, `enrollments` list |
| 6 | Record attendance | `Attendance` (PRESENT/ABSENT), `attendances` list |
| 7 | Record coursework (40) + exam (60) marks | `Result.coursework`, `Result.exam` |
| 8 | Auto calculate grades | `Result.calculateGrade()` -> A(5),B(4),C(3),D(2),F(0) |
| 9 | Generate transcripts | `generateTranscript(Student)` |
| 10 | Monitor academic performance | `calculateGPA()`, `getStanding()` |
| 11 | Generate academic reports | `generateReports()` - dept count, course avg, attendance % |
| 12 | Role-based authentication | `User`, `Role` enum, `login()` |
| 13 | Secure records | Private fields, auth check, enabled flag |
| 14 | Dashboards | `adminDashboard()`, `lecturerDashboard()`, `studentDashboard()` |

Grade Logic: `total = cw + exam` | >=80 A(5.0), 70-79 B(4.0), 60-69 C(3.0), 50-59 D(2.0), <50 F(0.0) | `GPA = Σ(gp*credits)/Σcredits`

## Quick Start
```bash
cd smart-campus-system
javac Main.java
java Main
```

### Expected Output
```
[Setup] Seeded: 2 Depts, 2 Programs, 3 Courses, 2 Students, 3 Enrollments
 SMART CAMPUS SYSTEM - SIMPLIFIED DEMO
 [12] ROLE-BASED AUTHENTICATION - Admin/Lecturer/Student OK
 [14] DASHBOARDS - Admin/Lecturer/Student views
 [9] TRANSCRIPT - CSC211 85 A, CSC212 70 B - GPA 4.57 First Class
 [11] REPORTS - dept, course avg, attendance %
 === Demo Complete - All 14 Objectives Shown ===
```

## Demo Data
- **Departments:** CSC Computer Science, MGT Management
- **Programs:** DCS Diploma in Computer Science, BCS
- **Courses:** CSC211 OOP (4cr), CSC212 DCN (3cr), CSC213 DB (3cr)
- **Lecturer:** Dr. Mukasa (assigned CSC211, CSC212)
- **Students:** 2025/DCS/DAY/0146 Ssegawa Tonny, 2444/G Kyarisiima Angella
- **Results:** Tonny 85 A + 70 B = GPA 4.57 First Class; Angella 65 C

## Target Users
- **Admin:** manage students/lecturers/depts/programs/courses/accounts/reports
- **Lecturer:** view assigned courses, view students, record attendance, enter marks
- **Student:** view profile, register courses, view attendance, view results/GPA, transcript

## For Submission
Repo: `KYARISIIMA-ANGELLA-2444-G` -> folder `smart-campus-system/Main.java`
Submit GitHub link: `https://github.com/Angel-Kyarisiima/KYARISIIMA-ANGELLA-2444-G/tree/main/smart-campus-system` on eCampus.

## Extensions (if needed)
- Add JDBC MySQL: replace lists with `StudentRepository` + `Connection`
- Add Spring Boot: wrap same model classes with `@Entity`
- Add JavaFX: reuse same classes for GUI

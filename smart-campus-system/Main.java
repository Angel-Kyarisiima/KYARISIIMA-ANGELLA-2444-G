import java.util.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import com.sun.net.httpserver.*;

enum Role { ADMIN, LECTURER, STUDENT }

class Student {
    String regNo, name, email;
    String department, program;
    List<Enrollment> enrollments = new ArrayList<>();
    Student(String regNo, String name, String email, String dept, String prog) {
        this.regNo = regNo; this.name = name; this.email = email;
        this.department = dept; this.program = prog;
    }
}

class Lecturer {
    String staffNo, name;
    List<Course> assignedCourses = new ArrayList<>();
    Lecturer(String staffNo, String name) { this.staffNo = staffNo; this.name = name; }
    void assignCourse(Course c) { assignedCourses.add(c); c.lecturer = this; }
}

class Department {
    String code, name;
    Department(String code, String name) { this.code = code; this.name = name; }
}

class Program {
    String code, name, department;
    Program(String code, String name, String dept) { this.code = code; this.name = name; this.department = dept; }
}

class Course {
    String code, name;
    int creditUnits;
    String department;
    Lecturer lecturer;
    Course(String code, String name, int credits, String dept) {
        this.code = code; this.name = name; this.creditUnits = credits; this.department = dept;
    }
}

class Enrollment {
    Student student; Course course; String semester; String year;
    Enrollment(Student s, Course c, String sem, String year) {
        this.student = s; this.course = c; this.semester = sem; this.year = year;
    }
}

class Attendance {
    Student student; Course course; String date; String status;
    Attendance(Student s, Course c, String date, String status) {
        this.student = s; this.course = c; this.date = date; this.status = status;
    }
}

class Result {
    Student student; Course course; String semester;
    double coursework;
    double exam;
    double total; String grade; double gradePoint;
    Result(Student s, Course c, String sem, double cw, double exam) {
        this.student = s; this.course = c; this.semester = sem;
        this.coursework = cw; this.exam = exam;
        calculateGrade();
    }
    void calculateGrade() {
        total = coursework + exam;
        if (total >= 80) { grade = "A"; gradePoint = 5.0; }
        else if (total >= 70) { grade = "B"; gradePoint = 4.0; }
        else if (total >= 60) { grade = "C"; gradePoint = 3.0; }
        else if (total >= 50) { grade = "D"; gradePoint = 2.0; }
        else { grade = "F"; gradePoint = 0.0; }
    }
}

class User {
    String username, password; Role role;
    String fullName;
    User(String u, String p, Role r, String fullName) { username = u; password = p; role = r; this.fullName = fullName; }
}

public class Main {
    static List<Student> students = new ArrayList<>();
    static List<Lecturer> lecturers = new ArrayList<>();
    static List<Department> departments = new ArrayList<>();
    static List<Program> programs = new ArrayList<>();
    static List<Course> courses = new ArrayList<>();
    static List<Enrollment> enrollments = new ArrayList<>();
    static List<Attendance> attendances = new ArrayList<>();
    static List<Result> results = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static Map<String, User> sessions = new HashMap<>();

    public static void main(String[] args) throws Exception {
        setupData();
        System.out.println("===============================================");
        System.out.println(" Smart Campus System");
        System.out.println(" Kyarisiima Angella - 2444/G");
        System.out.println("===============================================");

        adminDashboard();
        lecturerDashboard();
        studentDashboard();
        generateReports();
        
        System.out.println("\nDemo Complete");
        startServer();
    }

    static void setupData() {
        Department cs = new Department("CSC", "Computer Science");
        Department mgt = new Department("MGT", "Management");
        departments.add(cs); departments.add(mgt);
        programs.add(new Program("DCS", "Diploma in Computer Science", "CSC"));
        programs.add(new Program("BCS", "Bachelor of Computer Science", "CSC"));

        Course oop = new Course("CSC211", "Object Oriented Programming", 4, "CSC");
        Course dcn = new Course("CSC212", "Data Comm & Networking", 3, "CSC");
        Course db = new Course("CSC213", "Database Systems", 3, "CSC");
        courses.add(oop); courses.add(dcn); courses.add(db);

        Lecturer lec1 = new Lecturer("LEC001", "Dr. Namatovu");
        lec1.assignCourse(oop);
        lec1.assignCourse(dcn);
        lecturers.add(lec1);

        Student s1 = new Student("2025/DCS/DAY/0088", "Nalwoga Grace", "grace.nalwoga@campus.ug", "CSC", "DCS");
        Student s2 = new Student("2444/G", "Kyarisiima Angella", "angella@campus.ug", "CSC", "DCS");
        students.add(s1); students.add(s2);

        enrollments.add(new Enrollment(s1, oop, "Sem1", "2025/2026"));
        enrollments.add(new Enrollment(s1, dcn, "Sem1", "2025/2026"));
        enrollments.add(new Enrollment(s2, oop, "Sem1", "2025/2026"));
        enrollments.add(new Enrollment(s2, db, "Sem1", "2025/2026"));
        s1.enrollments.add(enrollments.get(0)); s1.enrollments.add(enrollments.get(1));
        s2.enrollments.add(enrollments.get(2)); s2.enrollments.add(enrollments.get(3));

        attendances.add(new Attendance(s1, oop, "2026-09-20", "PRESENT"));
        attendances.add(new Attendance(s1, oop, "2026-09-21", "PRESENT"));
        attendances.add(new Attendance(s1, dcn, "2026-09-20", "ABSENT"));
        attendances.add(new Attendance(s2, oop, "2026-09-20", "PRESENT"));
        attendances.add(new Attendance(s2, db, "2026-09-21", "PRESENT"));

        results.add(new Result(s1, oop, "Sem1", 30, 55));
        results.add(new Result(s1, dcn, "Sem1", 28, 42));
        results.add(new Result(s2, oop, "Sem1", 25, 40));
        results.add(new Result(s2, db, "Sem1", 32, 48));

        users.add(new User("admin", "admin123", Role.ADMIN, "System Admin"));
        users.add(new User("lecturer1", "lecturer123", Role.LECTURER, "Dr. Namatovu"));
        users.add(new User("angella", "student123", Role.STUDENT, "Kyarisiima Angella"));

        System.out.println("Data loaded: " + students.size() + " students, " + courses.size() + " courses");
    }

    static User checkLogin(String u, String p) {
        for (User user : users) if (user.username.equals(u) && user.password.equals(p)) return user;
        return null;
    }

    static void adminDashboard() {
        System.out.println("\n -- Admin --");
        System.out.println(" Students: " + students.size() + " Lecturers: " + lecturers.size());
        System.out.println(" Departments: " + departments.size() + " Courses: " + courses.size());
    }
    static void lecturerDashboard() {
        Lecturer lec = lecturers.get(0);
        System.out.println("\n -- Lecturer: " + lec.name + " --");
        for (Course c : lec.assignedCourses) System.out.println("  " + c.code + " " + c.name);
    }
    static void studentDashboard() {
        for (Student s : students) {
            System.out.println("\n -- Student: " + s.name + " (" + s.regNo + ") --");
            for (Result r : results) if (r.student == s) {
                System.out.println("  " + r.course.code + " " + r.total + " " + r.grade);
            }
            System.out.println("  GPA: " + String.format("%.2f", getGPA(s)) + " " + getStanding(getGPA(s)));
        }
    }

    static double getGPA(Student s) {
        double points = 0; int credits = 0;
        for (Result r : results) if (r.student == s) {
            points += r.gradePoint * r.course.creditUnits;
            credits += r.course.creditUnits;
        }
        return credits == 0 ? 0.0 : Math.round(points / credits * 100.0) / 100.0;
    }
    static String getStanding(double gpa) {
        if (gpa >= 4.4) return "First Class";
        if (gpa >= 3.6) return "Second Class Upper";
        if (gpa >= 2.8) return "Second Class Lower";
        if (gpa >= 2.0) return "Pass";
        return "Retake";
    }

    static void generateReports() {
        System.out.println("\n -- Reports --");
        for (Department d : departments) {
            long count = students.stream().filter(s -> s.department.equals(d.code)).count();
            System.out.println("  " + d.name + ": " + count);
        }
    }

    static void startServer() throws Exception {
        int port = 8080;
        HttpServer server = null;
        try { server = HttpServer.create(new InetSocketAddress(port), 0); }
        catch (IOException e) { port = 8081; server = HttpServer.create(new InetSocketAddress(port), 0); }

        server.createContext("/", ex -> handleRoot(ex));
        server.createContext("/login", ex -> handleLogin(ex));
        server.createContext("/admin", ex -> handleAdmin(ex));
        server.createContext("/lecturer", ex -> handleLecturer(ex));
        server.createContext("/student", ex -> handleStudent(ex));
        server.createContext("/logout", ex -> handleLogout(ex));
        server.setExecutor(null);
        server.start();
        System.out.println("\nWeb running at http://localhost:" + port);
        System.out.println("Login with admin/admin123 , lecturer1/lecturer123 , angella/student123");
        Thread.currentThread().join();
    }

    static void handleRoot(HttpExchange ex) throws IOException {
        User u = getUserFromSession(ex);
        if (u == null) { redirect(ex, "/login"); return; }
        if (u.role == Role.ADMIN) redirect(ex, "/admin");
        else if (u.role == Role.LECTURER) redirect(ex, "/lecturer");
        else redirect(ex, "/student");
    }

    static void handleLogin(HttpExchange ex) throws IOException {
        if ("POST".equalsIgnoreCase(ex.getRequestMethod())) {
            String body = new String(ex.getRequestBody().readAllBytes());
            Map<String,String> p = parseForm(body);
            User u = checkLogin(p.get("username"), p.get("password"));
            if (u != null) {
                String sid = UUID.randomUUID().toString();
                sessions.put(sid, u);
                ex.getResponseHeaders().add("Set-Cookie", "SID=" + sid + "; Path=/");
                redirect(ex, "/");
                return;
            } else {
                sendHtml(ex, loginPage("Invalid username or password"));
                return;
            }
        }
        sendHtml(ex, loginPage(null));
    }

    static void handleAdmin(HttpExchange ex) throws IOException {
        User u = getUserFromSession(ex);
        if (u == null) { redirect(ex, "/login"); return; }
        if (u.role != Role.ADMIN) { sendHtml(ex, errorPage("Access denied - Admin only")); return; }
        sendHtml(ex, adminPage(u));
    }

    static void handleLecturer(HttpExchange ex) throws IOException {
        User u = getUserFromSession(ex);
        if (u == null) { redirect(ex, "/login"); return; }
        if (u.role != Role.LECTURER) { sendHtml(ex, errorPage("Access denied - Lecturer only")); return; }
        sendHtml(ex, lecturerPage(u));
    }

    static void handleStudent(HttpExchange ex) throws IOException {
        User u = getUserFromSession(ex);
        if (u == null) { redirect(ex, "/login"); return; }
        if (u.role != Role.STUDENT) { sendHtml(ex, errorPage("Access denied - Student only")); return; }
        Student s = null;
        for (Student st: students) if (st.email.equals(u.username + "@campus.ug") || st.name.equals(u.fullName) || st.regNo.equals("2444/G")) s = st;
        if (s == null) s = students.get(1);
        sendHtml(ex, studentPage(u, s));
    }

    static void handleLogout(HttpExchange ex) throws IOException {
        String sid = getSid(ex);
        if (sid != null) sessions.remove(sid);
        ex.getResponseHeaders().add("Set-Cookie", "SID=; Path=/; Max-Age=0");
        redirect(ex, "/login");
    }

    static User getUserFromSession(HttpExchange ex) {
        String sid = getSid(ex);
        if (sid == null) return null;
        return sessions.get(sid);
    }
    static String getSid(HttpExchange ex) {
        List<String> cookies = ex.getRequestHeaders().get("Cookie");
        if (cookies == null) return null;
        for (String c: cookies) {
            for (String part: c.split(";")) {
                part = part.trim();
                if (part.startsWith("SID=")) return part.substring(4);
            }
        }
        return null;
    }
    static Map<String,String> parseForm(String body) throws UnsupportedEncodingException {
        Map<String,String> m = new HashMap<>();
        if (body == null || body.isEmpty()) return m;
        for (String pair: body.split("&")) {
            String[] kv = pair.split("=",2);
            if (kv.length==2) m.put(URLDecoder.decode(kv[0],"UTF-8"), URLDecoder.decode(kv[1],"UTF-8"));
        }
        return m;
    }
    static void redirect(HttpExchange ex, String loc) throws IOException {
        ex.getResponseHeaders().add("Location", loc);
        ex.sendResponseHeaders(302, -1);
        ex.close();
    }
    static void sendHtml(HttpExchange ex, String html) throws IOException {
        byte[] b = html.getBytes("UTF-8");
        ex.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        ex.sendResponseHeaders(200, b.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(b); }
    }

    static String baseStyle() {
        return "<style>body{font-family:Arial;margin:0;background:#f0f2f5} .nav{background:#1a237e;color:#fff;padding:12px 20px;display:flex;justify-content:space-between} .nav a{color:#fff;text-decoration:none;margin-left:15px} .container{max-width:900px;margin:20px auto;padding:0 15px} .card{background:#fff;padding:16px;margin:12px 0;border-radius:6px;box-shadow:0 1px 3px rgba(0,0,0,0.12)} table{width:100%;border-collapse:collapse} th,td{padding:8px;border:1px solid #ddd} th{background:#e8eaf6} .badge{padding:3px 7px;border-radius:4px;color:#fff} .A{background:#2e7d32}.B{background:#558b2f}.C{background:#ff8f00;color:#000}.D{background:#e65100}.F{background:#b71c1c} input{padding:8px;width:100%;margin:6px 0;box-sizing:border-box} button{padding:9px 16px;background:#1a237e;color:#fff;border:none;border-radius:4px;cursor:pointer} .error{color:#c62828;background:#ffebee;padding:8px;border-radius:4px}</style>";
    }

    static String loginPage(String err) {
        return "<!doctype html><html><head><meta charset='utf-8'><title>Login - Smart Campus</title>" + baseStyle() + "</head><body>"
        + "<div class='nav'><span>Smart Campus - Kyarisiima Angella 2444/G</span></div>"
        + "<div class='container'><div class='card' style='max-width:400px;margin:40px auto'>"
        + "<h2>Login</h2>"
        + (err!=null? "<div class='error'>"+err+"</div>": "")
        + "<form method='POST' action='/login'>"
        + "<label>Username</label><input name='username' required placeholder='admin'>"
        + "<label>Password</label><input type='password' name='password' required placeholder='admin123'>"
        + "<button type='submit'>Login</button>"
        + "</form>"
        + "<p style='font-size:13px;color:#666;margin-top:12px'>Demo accounts:<br>admin / admin123 (Admin)<br>lecturer1 / lecturer123 (Lecturer)<br>angella / student123 (Student)</p>"
        + "</div></div></body></html>";
    }

    static String adminPage(User u) {
        StringBuilder h = new StringBuilder();
        h.append("<!doctype html><html><head><meta charset='utf-8'><title>Admin</title>").append(baseStyle()).append("</head><body>");
        h.append("<div class='nav'><span>Admin Dashboard - ").append(u.fullName).append("</span><span><a href='/admin'>Admin</a><a href='/logout'>Logout</a></span></div>");
        h.append("<div class='container'>");
        h.append("<div class='card'><h3>Overview</h3><p>Students: ").append(students.size()).append(" | Lecturers: ").append(lecturers.size()).append(" | Departments: ").append(departments.size()).append(" | Courses: ").append(courses.size()).append("</p></div>");
        h.append("<div class='card'><h3>Students</h3><table><tr><th>RegNo</th><th>Name</th><th>Dept</th><th>Program</th></tr>");
        for (Student s: students) h.append("<tr><td>").append(s.regNo).append("</td><td>").append(s.name).append("</td><td>").append(s.department).append("</td><td>").append(s.program).append("</td></tr>");
        h.append("</table></div>");
        h.append("<div class='card'><h3>Courses</h3><table><tr><th>Code</th><th>Name</th><th>Credits</th><th>Lecturer</th></tr>");
        for (Course c: courses) h.append("<tr><td>").append(c.code).append("</td><td>").append(c.name).append("</td><td>").append(c.creditUnits).append("</td><td>").append(c.lecturer!=null?c.lecturer.name:"-").append("</td></tr>");
        h.append("</table></div>");
        h.append("<div class='card'><h3>Reports</h3><p>Students per dept: ");
        for (Department d: departments) { long cnt = students.stream().filter(s->s.department.equals(d.code)).count(); h.append(d.name).append("=").append(cnt).append(" "); }
        h.append("</p></div>");
        h.append("</div></body></html>");
        return h.toString();
    }

    static String lecturerPage(User u) {
        Lecturer lec = lecturers.get(0);
        StringBuilder h = new StringBuilder();
        h.append("<!doctype html><html><head><meta charset='utf-8'><title>Lecturer</title>").append(baseStyle()).append("</head><body>");
        h.append("<div class='nav'><span>Lecturer - ").append(lec.name).append("</span><span><a href='/logout'>Logout</a></span></div>");
        h.append("<div class='container'>");
        h.append("<div class='card'><h3>My Courses</h3><ul>");
        for (Course c: lec.assignedCourses) h.append("<li>").append(c.code).append(" - ").append(c.name).append("</li>");
        h.append("</ul></div>");
        h.append("<div class='card'><h3>Students in my courses</h3><table><tr><th>Student</th><th>Course</th><th>Date</th><th>Attendance</th></tr>");
        for (Attendance a: attendances) if (lec.assignedCourses.contains(a.course)) h.append("<tr><td>").append(a.student.name).append("</td><td>").append(a.course.code).append("</td><td>").append(a.date).append("</td><td>").append(a.status).append("</td></tr>");
        h.append("</table></div>");
        h.append("<div class='card'><h3>Marks Entry</h3><table><tr><th>Student</th><th>Course</th><th>CW/40</th><th>Exam/60</th><th>Total</th><th>Grade</th></tr>");
        for (Result r: results) if (lec.assignedCourses.contains(r.course)) h.append("<tr><td>").append(r.student.name).append("</td><td>").append(r.course.code).append("</td><td>").append(r.coursework).append("</td><td>").append(r.exam).append("</td><td>").append(r.total).append("</td><td><span class='badge ").append(r.grade).append("'>").append(r.grade).append("</span></td></tr>");
        h.append("</table></div>");
        h.append("</div></body></html>");
        return h.toString();
    }

    static String studentPage(User u, Student s) {
        StringBuilder h = new StringBuilder();
        h.append("<!doctype html><html><head><meta charset='utf-8'><title>Student</title>").append(baseStyle()).append("</head><body>");
        h.append("<div class='nav'><span>Student - ").append(s.name).append(" (").append(s.regNo).append(")</span><span><a href='/logout'>Logout</a></span></div>");
        h.append("<div class='container'>");
        h.append("<div class='card'><h3>Profile</h3><p>").append(s.name).append(" | ").append(s.regNo).append(" | ").append(s.department).append(" / ").append(s.program).append(" | ").append(s.email).append("</p></div>");
        h.append("<div class='card'><h3>My Courses</h3><ul>");
        for (Enrollment e: enrollments) if (e.student==s) h.append("<li>").append(e.course.code).append(" - ").append(e.course.name).append("</li>");
        h.append("</ul></div>");
        h.append("<div class='card'><h3>Attendance</h3><table><tr><th>Course</th><th>Date</th><th>Status</th></tr>");
        for (Attendance a: attendances) if (a.student==s) h.append("<tr><td>").append(a.course.code).append("</td><td>").append(a.date).append("</td><td>").append(a.status).append("</td></tr>");
        h.append("</table></div>");
        h.append("<div class='card'><h3>Results</h3><table><tr><th>Code</th><th>Course</th><th>CW</th><th>Exam</th><th>Total</th><th>Grade</th></tr>");
        for (Result r: results) if (r.student==s) h.append("<tr><td>").append(r.course.code).append("</td><td>").append(r.course.name).append("</td><td>").append(r.coursework).append("</td><td>").append(r.exam).append("</td><td>").append(r.total).append("</td><td><span class='badge ").append(r.grade).append("'>").append(r.grade).append("</span></td></tr>");
        h.append("</table><p><b>GPA: ").append(String.format("%.2f", getGPA(s))).append(" - ").append(getStanding(getGPA(s))).append("</b></p></div>");
        h.append("<div class='card'><h3>Transcript</h3><table><tr><th>Code</th><th>Course</th><th>Credits</th><th>Grade</th><th>Point</th></tr>");
        for (Result r: results) if (r.student==s) h.append("<tr><td>").append(r.course.code).append("</td><td>").append(r.course.name).append("</td><td>").append(r.course.creditUnits).append("</td><td>").append(r.grade).append("</td><td>").append(r.gradePoint).append("</td></tr>");
        h.append("</table></div>");
        h.append("</div></body></html>");
        return h.toString();
    }

    static String errorPage(String msg) {
        return "<!doctype html><html><head><meta charset='utf-8'><title>Error</title>" + baseStyle() + "</head><body><div class='container'><div class='card'><p class='error'>" + msg + "</p><a href='/login'>Back to login</a></div></div></body></html>";
    }
}

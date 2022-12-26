package mk.ukim.finki.lab.web.servlet;

import mk.ukim.finki.lab.model.Course;
import mk.ukim.finki.lab.model.Grade;
import mk.ukim.finki.lab.model.Student;
import mk.ukim.finki.lab.service.CourseService;
import mk.ukim.finki.lab.service.GradeService;
import mk.ukim.finki.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "studentEnrollment", urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet {

    private final CourseService courseService;
    private final StudentService studentService;
    private final GradeService gradeService;
    private final SpringTemplateEngine springTemplateEngine;

    public StudentEnrollmentSummaryServlet(CourseService courseService, StudentService studentService, GradeService gradeService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.gradeService = gradeService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html");
        Long courseID = Long.parseLong(req.getSession().getAttribute("courseId").toString());
        String param = req.getParameter("student");
        Course course = courseService.getById(courseID);
        webContext.setVariable("courseName", course.getName());
        webContext.setVariable("studentsInCourse", courseService.listStudentsByCourse(courseID));

        Map<String, Grade> grades = new HashMap<>();

        for (Student s : courseService.getById(courseID).getStudents()) {
            Grade grade = gradeService.findByCourseIdAndStudentUsername(courseID, s.getUsername());
            grades.put(s.getUsername(), grade);
        }
        webContext.setVariable("formatter", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        webContext.setVariable("grades", grades);
        this.springTemplateEngine.process("studentsInCourse.html", webContext, resp.getWriter());
    }
}

package mk.ukim.finki.lab.web.servlet;

import mk.ukim.finki.lab.service.CourseService;
import mk.ukim.finki.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "listStudent", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, this.getServletContext());
        resp.setContentType("text/html");
        long courseId = (long) req.getSession().getAttribute("courseId");
        webContext.setVariable("courseId", courseId);
        webContext.setVariable("students", studentService.listAll());
        springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("student");
        Long courseID = Long.parseLong(req.getSession().getAttribute("courseId").toString());
        courseService.addStudentInCourse(parameter, courseID);
        resp.sendRedirect("/studentEnrollmentSummary");
    }
}

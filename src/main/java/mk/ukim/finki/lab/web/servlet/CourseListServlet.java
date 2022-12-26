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

@WebServlet(name = "listCourses", urlPatterns = "/listCourses")
public class CourseListServlet extends HttpServlet {

    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public CourseListServlet(CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html");
        webContext.setVariable("courses", courseService.listAll());
        this.springTemplateEngine.process("listCourses.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long courseId = Long.valueOf(req.getParameter("course"));
        req.getSession().setAttribute("courseId", courseId);
        resp.sendRedirect("/addStudent");
    }
}

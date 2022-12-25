package mk.ukim.finki.lab.web.controller;

import mk.ukim.finki.lab.model.Course;
import mk.ukim.finki.lab.model.Grade;
import mk.ukim.finki.lab.model.Student;
import mk.ukim.finki.lab.service.CourseService;
import mk.ukim.finki.lab.service.GradeService;
import mk.ukim.finki.lab.service.StudentService;
import mk.ukim.finki.lab.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private static final List<String> types = List.of("WINTER", "SUMMER", "MANDATORY", "ELECTIVE");
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GradeService gradeService;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService, GradeService gradeService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error,
                                 Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("courses", courseService.listAll());
        return "listCourses";
    }

    @PostMapping
    public String chooseCourse(@RequestParam long courseId,
                               HttpSession session) {
        session.setAttribute("courseId", courseId);
        return "redirect:/addStudent";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable long id) {
        List<Grade> grades = gradeService.findByCourseId(id);
        gradeService.delete(grades);
        courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/add-form")
    public String getAddCoursePage(Model model) {
        model.addAttribute("teachers", this.teacherService.findAll());
        model.addAttribute("types", types);
        return "add-course";
    }

    @PostMapping("/add")
    public String saveCourse(
            @RequestParam String name,
            @RequestParam String desc,
            @RequestParam Long teacher,
            @RequestParam String type,
            @RequestParam(required = false) Long id) {

        if (id == null) {
            this.courseService.addCourse(name, desc, teacher, type);
        } else {
            this.courseService.editCourse(id, name, desc, teacher, type);
        }

        return "redirect:/courses";

    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id,
                                    Model model) {
        if (this.courseService.getById(id) != null) {
            model.addAttribute("course", this.courseService.getById(id));
            model.addAttribute("teachers", this.teacherService.findAll());
            model.addAttribute("types", types);
            return "add-course";
        }
        return "redirect:/courses?error=CourseNotFound";
    }

    @GetMapping("/addGrade")
    public String getGradePage(Model model) {
        model.addAttribute("courses", courseService.listAll());
        model.addAttribute("students", studentService.listAll());

        return "add-grade";
    }


    @PostMapping("/addGrade")
    public String addGrade(@RequestParam Long course,
                           @RequestParam String student,
                           @RequestParam Character grade,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        Course c = courseService.getById(course);
        Student s = studentService.searchByUsername(student);
        Grade g = new Grade(grade, s, c, date);

        gradeService.save(g);

        return "redirect:/courses";
    }
}

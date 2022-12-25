package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Course;
import mk.ukim.finki.lab.model.Student;
import mk.ukim.finki.lab.model.Type;
import mk.ukim.finki.lab.repository.impl.StudentRepository;
import mk.ukim.finki.lab.repository.jpa.CourseRepositoryJpa;
import mk.ukim.finki.lab.repository.jpa.TeacherRepositoryJpa;
import mk.ukim.finki.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepositoryJpa courseRepositoryJpa;
    private final StudentRepository studentRepository;
    private final TeacherRepositoryJpa teacherRepository;

    public CourseServiceImpl(CourseRepositoryJpa courseRepositoryJpa, StudentRepository studentRepository, TeacherRepositoryJpa teacherRepository) {
        this.courseRepositoryJpa = courseRepositoryJpa;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Course> listAll() {
        return courseRepositoryJpa.findAll();
    }

    @Override
    public Course getById(Long id) {
        return courseRepositoryJpa.findCourseByCourseId(id);
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course c = courseRepositoryJpa.findById(courseId).orElse(null);

        if (c == null)
            return Collections.emptyList();

        return c.getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student student = studentRepository.findByUsername(username);

        if (student == null)
            return null;

        Course course = courseRepositoryJpa.findById(courseId).orElse(null);

        if (course == null)
            return null;

        course.getStudents().removeIf(s -> s.getUsername().equals(student.getUsername()));
        course.getStudents().add(student);
        courseRepositoryJpa.save(course);
        return course;
    }

    @Override
    public void deleteById(Long courseId) {
        courseRepositoryJpa.deleteById(courseId);
    }

    @Override
    public Course addCourse(String name, String description, Long teacherId, String type) {
        Course course = new Course(name, description, teacherRepository.getById(teacherId), Type.valueOf(type));
        return courseRepositoryJpa.save(course);
    }

    @Override
    public Course editCourse(Long id, String name, String description, Long teacher, String type) {
        Course course = courseRepositoryJpa.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacherRepository.getById(teacher));
        course.setType(Type.valueOf(type));
        return courseRepositoryJpa.save(course);
    }


    @Override
    public void save(String name, String description, Long teacherId, Long courseId, String type) {
        Course course = courseRepositoryJpa.findById(courseId).orElse(null);

        if (course == null) {
            course = new Course();
        }

        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacherRepository.getById(teacherId));
        course.setType(Type.valueOf(type));
        courseRepositoryJpa.save(course);
    }
}

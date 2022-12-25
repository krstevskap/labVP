package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Course;
import mk.ukim.finki.lab.model.Student;

import java.util.List;

public interface CourseService {

    List<Course> listAll();

    Course getById(Long id);

    List<Student> listStudentsByCourse(Long courseId);

    Course addStudentInCourse(String username, Long courseId);

    void deleteById(Long courseId);

    Course addCourse(String name, String description, Long teacherId, String type);

    Course editCourse(Long id, String name, String description, Long teacher, String type);

    void save(String name, String description, Long teacherId, Long courseId, String type);

}

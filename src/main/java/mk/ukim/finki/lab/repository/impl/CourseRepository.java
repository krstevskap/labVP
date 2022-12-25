package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Course;
import mk.ukim.finki.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {



    public List<Course> findAllCourses() {
        return DataHolder.courseList;
    }

    public Course findById(Long courseId) {
        return DataHolder.courseList.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        Course course = DataHolder.courseList.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst().orElse(null);

        if (course == null)
            return null;

        return course.getStudents();
    }

    public Course addStudentToCourse(Student student, Course course) {
        course.getStudents().add(student);
        return course;
    }

    public boolean deleteById(Long id) {
        Course course = DataHolder.courseList.stream().filter(c -> c.getCourseId().equals(id)).findFirst().orElse(null);

        if (course == null) {
            return false;
        }

        DataHolder.courseList.remove(course);
        return true;
    }

    public Course addCourse(Course course) {
        DataHolder.courseList.add(course);
        return course;
    }
}

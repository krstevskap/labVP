package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Course;
import mk.ukim.finki.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryJpa extends JpaRepository<Course, Long> {

    Course findCourseByCourseId(Long courseId);


}

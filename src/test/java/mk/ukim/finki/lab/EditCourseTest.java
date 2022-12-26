package mk.ukim.finki.lab;

import mk.ukim.finki.lab.model.Course;
import mk.ukim.finki.lab.model.Type;
import mk.ukim.finki.lab.repository.impl.StudentRepository;
import mk.ukim.finki.lab.repository.jpa.CourseRepositoryJpa;
import mk.ukim.finki.lab.repository.jpa.TeacherRepositoryJpa;
import mk.ukim.finki.lab.service.impl.CourseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EditCourseTest {

    @Mock
    private CourseRepositoryJpa courseRepositoryJpa;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepositoryJpa teacherRepository;

    private CourseServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
//        Course course = new Course();
//        course.setCourseId(3L);
//        course.setName("Calculus");
//        course.setDescription("c");
//        course.setTeacher(null);
//        course.setType(Type.ELECTIVE);

        Course course = new Course("Calculus","c",null,Type.ELECTIVE);
        course.setCourseId(3L);
        course.setStudents(null);

        Mockito.when(this.courseRepositoryJpa.save(Mockito.any(Course.class))).thenReturn(course);
        Mockito.when(courseRepositoryJpa.findById(3L)).thenReturn(Optional.of(course));

        service = Mockito.spy(new CourseServiceImpl(this.courseRepositoryJpa, this.studentRepository, this.teacherRepository));
    }

    @Test
    public void testEditCourse() {
        Course course = new Course("Calculus","c",null,Type.ELECTIVE);
        course.setCourseId(3L);
        course.setStudents(null);

        Course c = service.editCourse(3L, "Calculus", "c", null, "ELECTIVE");
        Mockito.verify(service).editCourse(3L, "Calculus", "c", null, "ELECTIVE");

        Assert.assertEquals(course, c);
    }

}

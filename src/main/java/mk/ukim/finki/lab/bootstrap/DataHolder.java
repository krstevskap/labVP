package mk.ukim.finki.lab.bootstrap;

import mk.ukim.finki.lab.model.Course;
import mk.ukim.finki.lab.model.Student;
import mk.ukim.finki.lab.model.Teacher;
import mk.ukim.finki.lab.model.Type;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Student> studentList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();

    @PostConstruct
    public void init() {
        studentList.add(new Student("user1", "u1", "SName1", "Surname1"));
        studentList.add(new Student("user2", "u2", "SName2", "Surname2"));
        studentList.add(new Student("user3", "u3", "SName3", "Surname3"));
        studentList.add(new Student("user4", "u4", "SName4", "Surname4"));
        studentList.add(new Student("user5", "u5", "SName5", "Surname5"));


        teacherList.add(new Teacher("TName1", "Surname1"));
        teacherList.add(new Teacher("TName2", "Surname2"));
        teacherList.add(new Teacher("TName3", "Surname3"));
        teacherList.add(new Teacher("TName4", "Surname4"));
        teacherList.add(new Teacher("TName5", "Surname5"));

        courseList.add(new Course("Web Programming", "wp", teacherList.get(1), Type.ELECTIVE));
        courseList.add(new Course("Calculus", "c", teacherList.get(1), Type.MANDATORY));
        courseList.add(new Course("Operating Systems", "os", teacherList.get(2), Type.SUMMER));
        courseList.add(new Course("Discrete Math", "dm", teacherList.get(3), Type.WINTER));
        courseList.add(new Course("Internet Technology", "it", teacherList.get(4), Type.MANDATORY));

    }
}

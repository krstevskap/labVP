package mk.ukim.finki.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    @ManyToOne
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description) {
//        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
    }

    public Course(String name, String description, Teacher teacher) {
//        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.students = new ArrayList<>();
        this.teacher = teacher;
    }

    public Course(String name, String description, Teacher teacher, Type type) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.type = type;
//        this.courseId = (long) (Math.random() * 1000);
        this.students = new ArrayList<>();
    }

    public Course() {
    }
}

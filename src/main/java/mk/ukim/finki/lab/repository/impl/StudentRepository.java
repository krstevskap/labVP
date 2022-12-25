package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    public List<Student> findAllStudents() {
        return DataHolder.studentList;
    }

    public Student findByUsername(String username) {
        return DataHolder.studentList.stream().filter(s -> s.getUsername().equals(username)).findFirst().orElse(null);
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return DataHolder.studentList.stream()
                .filter(s -> s.getName().contains(text) || s.getSurname().contains(text))
                .collect(Collectors.toList());
    }

    public Student save(String username, String password, String name, String surname) {
        if (username == null || password == null || name == null || surname == null)
            return null;

        DataHolder.studentList.removeIf(s -> s.getUsername().equals(username));
        Student s = new Student(username, password, name, surname);
        DataHolder.studentList.add(s);

        return s;
    }

}

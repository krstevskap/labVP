package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> listAll();

    List<Student> searchByNameOrSurname(String text);

    Student save(String username, String password, String name, String surname);

    Student searchByUsername(String username);
}

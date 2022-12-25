package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Student;
import mk.ukim.finki.lab.repository.impl.StudentRepository;
import mk.ukim.finki.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryJpa studentRepository;

    public StudentServiceImpl(StudentRepositoryJpa studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findAllByUsernameOrSurname(text, text);
    }


    @Override
    public Student save(String username, String password, String name, String surname) {
        Student s = new Student(username,password,name,surname);
        return studentRepository.save(s);
    }

    @Override
    public Student searchByUsername(String username) {
        return studentRepository.findByUsername(username);
    }
}

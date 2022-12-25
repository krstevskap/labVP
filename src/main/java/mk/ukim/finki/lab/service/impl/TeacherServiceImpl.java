package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Teacher;
import mk.ukim.finki.lab.repository.jpa.TeacherRepositoryJpa;
import mk.ukim.finki.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryJpa teacherRepository;

    public TeacherServiceImpl(TeacherRepositoryJpa teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}

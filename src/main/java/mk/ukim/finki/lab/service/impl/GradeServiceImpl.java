package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Grade;
import mk.ukim.finki.lab.repository.jpa.GradeRepositoryJpa;
import mk.ukim.finki.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepositoryJpa gradeRepositoryJpa;

    public GradeServiceImpl(GradeRepositoryJpa gradeRepositoryJpa) {
        this.gradeRepositoryJpa = gradeRepositoryJpa;
    }

    @Override
    public List<Grade> findByCourseId(Long id) {
        return gradeRepositoryJpa.findAllByCourse_CourseId(id);
    }

    @Override
    public Grade findByCourseIdAndStudentUsername(Long id, String username) {
        return gradeRepositoryJpa.findByCourse_CourseIdAndStudent_Username(id, username);
    }

    @Override
    public void save(Grade grade) {
        gradeRepositoryJpa.save(grade);
    }

    @Override
    public void delete(List<Grade> grades) {
        gradeRepositoryJpa.deleteAll(grades);
    }

    @Override
    public void delete(Grade grade) {
        gradeRepositoryJpa.delete(grade);
    }
}

package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> findByCourseId(Long id);

    Grade findByCourseIdAndStudentUsername(Long id, String username);

    void save(Grade grade);

    void delete(List<Grade> grades);
}

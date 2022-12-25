package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GradeRepositoryJpa extends JpaRepository<Grade, Long> {

    List<Grade> findAllByCourse_CourseId(Long courseId);

    Grade findByCourse_CourseIdAndStudent_Username(Long courseId, String username);

    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);
}

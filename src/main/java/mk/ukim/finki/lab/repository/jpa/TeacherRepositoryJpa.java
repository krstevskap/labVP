package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepositoryJpa extends JpaRepository<Teacher,Long> {

    Teacher getById(Long id);
}

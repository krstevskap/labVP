package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryJpa extends JpaRepository<Student, Long> {
    List<Student> findAllByUsernameOrSurname(String text,String text2);

    Student findByUsername(String username);
}

package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepository {

    public List<Teacher> findAll() {
        return DataHolder.teacherList;
    }

    public Teacher findById(Long id) {
        return DataHolder.teacherList.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
}

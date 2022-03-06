package tech.ericwathome.moringaschool.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ericwathome.moringaschool.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByEmailIgnoreCase(String studentEmail);
    void deleteStudentByEmailIgnoreCase(String studentEmail);
}

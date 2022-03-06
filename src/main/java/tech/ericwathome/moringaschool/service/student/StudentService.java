package tech.ericwathome.moringaschool.service.student;

import tech.ericwathome.moringaschool.entity.Student;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student) throws EmptyParameterException;

    List<Student> allStudents();

    Student findStudentById(Long id) throws StudentNotFoundException;

    Student findStudentByEmail(String studentEmail) throws StudentNotFoundException;

    Student updateStudentById(Long id, Student studentUpdate) throws StudentNotFoundException;

    Student updateStudentByEmail(String studentEmail, Student studentUpdate) throws StudentNotFoundException;

    Student deleteStudentById(Long id) throws StudentNotFoundException;

    Student deleteStudentByEmail(String studentEmail) throws StudentNotFoundException;
}

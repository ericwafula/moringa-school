package tech.ericwathome.moringaschool.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ericwathome.moringaschool.entity.Student;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.StudentNotFoundException;
import tech.ericwathome.moringaschool.repository.student.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) throws EmptyParameterException {
        if (student.getName().equals("") || student.getEmail().isEmpty()) {
            throw new EmptyParameterException("can not save student with an empty parameter");
        } else {
            studentRepository.save(student);
        }
        return student;
    }

    @Override
    public List<Student> allStudents() {
        List<Student> allStudents = studentRepository.findAll();
        Student emptyStudent = Student.builder()
                .name("No student found")
                .email("No student found")
                .build();
        List<Student> emptyStudentList = new ArrayList<>();
        emptyStudentList.add(emptyStudent);

        if (allStudents.isEmpty()) {
            return new ArrayList<>(emptyStudentList);
        }

        return allStudents;
    }

    @Override
    public Student findStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        student.orElseThrow(() -> new StudentNotFoundException("NO_STUDENT_FOUND_WITH_PROVIDED_ID: " + id));
        return student.get();
    }

    @Override
    public Student findStudentByEmail(String studentEmail) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findStudentByEmailIgnoreCase(studentEmail);
        student.orElseThrow(() -> new StudentNotFoundException("NO_STUDENT_FOUND_WITH_PROVIDED_EMAIL: " + studentEmail));
        return student.get();
    }

    @Override
    public Student updateStudentById(Long id, Student studentUpdate) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        student.orElseThrow(() -> new StudentNotFoundException("NO_STUDENT_FOUND_WITH_PROVIDED_ID: " + id));
        if (!studentUpdate.getName().isEmpty()) {
            student.get().setName(studentUpdate.getName());
        }
        if (!studentUpdate.getEmail().isEmpty()) {
            student.get().setEmail(studentUpdate.getEmail());
        }

        return studentRepository.save(student.get());
    }

    @Override
    public Student updateStudentByEmail(String studentEmail, Student studentUpdate) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findStudentByEmailIgnoreCase(studentEmail);
        student.orElseThrow(() -> new StudentNotFoundException("NO_STUDENT_FOUND_WITH_PROVIDED_EMAIL: " + studentEmail));
        if (!studentUpdate.getName().isEmpty()) {
            student.get().setName(studentUpdate.getName());
        }
        if (!studentUpdate.getEmail().isEmpty()) {
            student.get().setEmail(studentUpdate.getEmail());
        }
        return studentRepository.save(student.get());
    }

    @Override
    public Student deleteStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        student.orElseThrow(() -> new StudentNotFoundException("NO_STUDENT_FOUND_WITH_PROVIDED_ID: " + id));
        studentRepository.deleteById(id);
        return student.get();
    }

    @Override
    public Student deleteStudentByEmail(String studentEmail) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findStudentByEmailIgnoreCase(studentEmail);
        student.orElseThrow(() -> new StudentNotFoundException("NO_STUDENT_FOUND_WITH_PROVIDED_EMAIL: " + studentEmail));
        Long id = student.get().getId();
        studentRepository.deleteById(id);
        return student.get();
    }
}

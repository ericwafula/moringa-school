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
        if (student.get().getName().equals("") && student.get().getEmail().equals("")) throw new StudentNotFoundException("no student matches the provided id");
        return student.get();
    }

    @Override
    public Student findStudentByEmail(String studentEmail) throws StudentNotFoundException {
        Student student = studentRepository.findStudentByEmailIgnoreCase(studentEmail);
        if (student == null) throw new StudentNotFoundException("no student matches the provided email address");
        return student;
    }

    @Override
    public Student updateStudentById(Long id, Student studentUpdate) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.get().getName().equals("") && student.get().getEmail().equals("")) throw new StudentNotFoundException("no student matches the provided id");
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
        Student student = studentRepository.findStudentByEmailIgnoreCase(studentEmail);
        if (student == null) throw new StudentNotFoundException("no student matches the provided email address");
        if (!studentUpdate.getName().isEmpty()) {
            student.setName(studentUpdate.getName());
        }
        if (!studentUpdate.getEmail().isEmpty()) {
            student.setEmail(studentUpdate.getEmail());
        }
        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.get().getName().equals("") && student.get().getEmail().equals("")) throw new StudentNotFoundException("cannot delete student with non-existent id");
        studentRepository.deleteById(id);
        return student.get();
    }

    @Override
    public Student deleteStudentByEmail(String studentEmail) throws StudentNotFoundException {
        Student student = studentRepository.findStudentByEmailIgnoreCase(studentEmail);
        if (student == null) throw new StudentNotFoundException("cannot delete student with non-existent email address");
        Long id = student.getId();
        studentRepository.deleteById(id);
        return student;
    }
}

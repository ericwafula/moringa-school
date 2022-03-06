package tech.ericwathome.moringaschool.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ericwathome.moringaschool.entity.Student;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.StudentNotFoundException;
import tech.ericwathome.moringaschool.service.student.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student/new")
    public Student addStudent(@RequestBody Student student) throws EmptyParameterException {
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> allStudents() {
        return studentService.allStudents();
    }

    @GetMapping("/student/id/{id}")
    public Student findStudentById(@PathVariable("id") Long id) throws StudentNotFoundException {
        return studentService.findStudentById(id);
    }

    @GetMapping("/student/email/{email}")
    public Student findStudentByEmail(@PathVariable("email") String studentEmail) throws StudentNotFoundException {
        return studentService.findStudentByEmail(studentEmail);
    }

    @PutMapping("/student/id/{id}")
    public Student updateStudentById(@PathVariable Long id, @RequestBody Student studentUpdate) throws StudentNotFoundException {
        return studentService.updateStudentById(id, studentUpdate);
    }

    @PutMapping("/student/email/{email}")
    public Student updateStudentByEmail(@PathVariable("email") String studentEmail, @RequestBody Student studentUpdate) throws StudentNotFoundException {
        return studentService.updateStudentByEmail(studentEmail, studentUpdate);
    }

    @DeleteMapping("/student/id/{id}")
    public Student deleteStudentById(@PathVariable("id") Long id) throws StudentNotFoundException {
        return studentService.deleteStudentById(id);
    }

    @DeleteMapping("/student/email/{email}")
    public Student deleteStudentByEmail(@PathVariable("email") String studentEmail) throws StudentNotFoundException {
        return studentService.deleteStudentByEmail(studentEmail);
    }
}

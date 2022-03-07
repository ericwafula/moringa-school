package tech.ericwathome.moringaschool.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ericwathome.moringaschool.entity.Course;
import tech.ericwathome.moringaschool.entity.Student;
import tech.ericwathome.moringaschool.entity.TechnicalMentor;
import tech.ericwathome.moringaschool.error.CourseNotFoundException;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.StudentNotFoundException;
import tech.ericwathome.moringaschool.error.TechnicalMentorNotFoundException;
import tech.ericwathome.moringaschool.service.course.CourseService;
import tech.ericwathome.moringaschool.service.student.StudentService;
import tech.ericwathome.moringaschool.service.technicalmentor.TechnicalMentorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;
    private final TechnicalMentorService technicalMentorService;

    @Autowired
    public CourseController(CourseService courseService, StudentService studentService, TechnicalMentorService technicalMentorService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.technicalMentorService = technicalMentorService;
    }

    @PostMapping("/course/new")
    public Course addCourse(@RequestBody Course course) throws EmptyParameterException {
        return courseService.addCourse(course);
    }

    @GetMapping
    public List<Course> allCourses() {
        return courseService.allCourses();
    }

    @GetMapping("/course/id/{id}")
    public Course findCourseById(@PathVariable("id") Long courseId) throws CourseNotFoundException {
        return courseService.findCourseById(courseId);
    }

    @GetMapping("/course/name/{name}")
    public Course findCourseByName(@PathVariable("name") String name) throws CourseNotFoundException {
        return courseService.findCourseByName(name);
    }

    @GetMapping("/course/id/{id}/technical-mentors")
    public List<TechnicalMentor> findAllTechnicalMentorsInCourse(@PathVariable("id") Long id) throws CourseNotFoundException {
        Course course = findCourseById(id);
        if (course == null) throw new CourseNotFoundException("No course found with provided id");
        List<TechnicalMentor> allTechnicalMentors = course.getTechnicalMentors();
        if (allTechnicalMentors.isEmpty()) {
            TechnicalMentor emptyTechnicalMentorMessage = TechnicalMentor.builder()
                    .name("No technical mentors assigned")
                    .email("No technical mentors assigned")
                    .build();
            List<TechnicalMentor> emptyTechnicalMentorList = new ArrayList<>();
            emptyTechnicalMentorList.add(emptyTechnicalMentorMessage);
            return emptyTechnicalMentorList;
        }
        return allTechnicalMentors;
    }

    @GetMapping("/course/id/{courseId}/students")
    public List<Student> findAllStudentsEnrolledInCourse(@PathVariable("courseId") Long courseId) throws CourseNotFoundException {
        Course course = courseService.findCourseById(courseId);
        if (course == null) throw new CourseNotFoundException("No course found with provided id");
        List<Student> allStudents = course.getStudents();
        if (allStudents.isEmpty()) {
            Student emptyStudentMessage = Student.builder()
                    .name("No students enrolled")
                    .email("No students enrolled")
                    .build();
            List<Student> emptyStudentsList = new ArrayList<>();
            emptyStudentsList.add(emptyStudentMessage);
            return emptyStudentsList;
        }
        return allStudents;
    }

    @PutMapping("/course/id/{id}")
    public Course updateCourseById(@PathVariable("id") Long id, @RequestBody Course updatedCourse) throws CourseNotFoundException {
        return courseService.updateCourseById(id, updatedCourse);
    }

    @PutMapping("/course/name/{name}")
    public Course updateCourseByName(@PathVariable("name") String name, @RequestBody Course updatedCourse) throws CourseNotFoundException {
        return courseService.updateCourseByName(name, updatedCourse);
    }

    @PutMapping("/course/id/{courseId}/students/student/new")
    public Student addStudentToCourse(@PathVariable("courseId") Long courseId, @RequestBody Student student) throws CourseNotFoundException, StudentNotFoundException {
        Course course = courseService.findCourseById(courseId);
        Student confirmStudentExists = studentService.findStudentById(student.getId());
        if (confirmStudentExists == null) {
            throw new StudentNotFoundException("can't add student to course because student doesn't exist");
        } else {
//            student.setCourseId(courseId);
            course.addStudent(student);
            courseService.updateCourseById(courseId, course);
        }

        return student;
    }

    @PutMapping("/course/id/{courseId}/technical-mentors/technical-mentor/id/{technicalMentorId}/new")
    public TechnicalMentor addTechnicalMentorToCourse(@PathVariable("courseId") Long courseId, @PathVariable("technicalMentorId") Long technicalMentorId) throws CourseNotFoundException, TechnicalMentorNotFoundException {
        Course course = courseService.findCourseById(courseId);
        TechnicalMentor technicalMentor = technicalMentorService.findTechnicalMentorById(technicalMentorId);
        if (course == null) throw new CourseNotFoundException("no course found with provided id");
        if (technicalMentor == null) {
            throw new TechnicalMentorNotFoundException("can't add technical mentor because technical mentor does not exist");
        } else {
            course.addTechnicalMentor(technicalMentor);
            courseService.updateCourseById(courseId, course);
        }
        return technicalMentor;
    }

    @DeleteMapping("/course/id/{id}")
    public Course deleteCourseById(@PathVariable("id") Long courseId) throws CourseNotFoundException {
        return courseService.deleteCourseById(courseId);
    }

    @DeleteMapping("/course/name/{name}")
    public Course deleteCourseByName(@PathVariable("name") String courseName) throws CourseNotFoundException {
        return courseService.deleteCourseByName(courseName);
    }
}

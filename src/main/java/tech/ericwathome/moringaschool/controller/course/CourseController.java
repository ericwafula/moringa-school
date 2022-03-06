package tech.ericwathome.moringaschool.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ericwathome.moringaschool.entity.Course;
import tech.ericwathome.moringaschool.entity.Student;
import tech.ericwathome.moringaschool.error.CourseNotFoundException;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.StudentNotFoundException;
import tech.ericwathome.moringaschool.service.course.CourseService;
import tech.ericwathome.moringaschool.service.student.StudentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
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

    @PutMapping("/course/id/{id}")
    public Course updateCourseById(@PathVariable("id") Long id, @RequestBody Course updatedCourse) throws CourseNotFoundException {
        return courseService.updateCourseById(id, updatedCourse);
    }

    @PutMapping("/course/name/{name}")
    public Course updateCourseByName(@PathVariable("name") String name, @RequestBody Course updatedCourse) throws CourseNotFoundException {
        return courseService.updateCourseByName(name, updatedCourse);
    }

    @PutMapping("course/id/{courseId}/students/student/new")
    public Student addStudentToCourse(@PathVariable("courseId") Long courseId, @RequestBody Student student) throws CourseNotFoundException, StudentNotFoundException {
        Course course = courseService.findCourseById(courseId);
        Student confirmStudentExists = studentService.findStudentById(student.getId());
        if (confirmStudentExists == null) {
            throw new StudentNotFoundException("can't add student to course because student doesn't exist");
        } else {
            student.setCourseId(courseId);
            course.addStudent(student);
            courseService.updateCourseById(courseId, course);
        }

        return student;
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

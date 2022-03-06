package tech.ericwathome.moringaschool.service.course;

import tech.ericwathome.moringaschool.entity.Course;
import tech.ericwathome.moringaschool.error.CourseNotFoundException;
import tech.ericwathome.moringaschool.error.EmptyParameterException;

import java.util.List;

public interface CourseService {
    Course addCourse(Course course) throws EmptyParameterException;

    List<Course> allCourses();

    Course findCourseById(Long courseId) throws CourseNotFoundException;

    Course findCourseByName(String name) throws CourseNotFoundException;

    Course updateCourseById(Long id, Course updatedCourse) throws CourseNotFoundException;

    Course updateCourseByName(String name, Course updatedCourse) throws CourseNotFoundException;

    Course deleteCourseById(Long courseId) throws CourseNotFoundException;

    Course deleteCourseByName(String courseName) throws CourseNotFoundException;
}

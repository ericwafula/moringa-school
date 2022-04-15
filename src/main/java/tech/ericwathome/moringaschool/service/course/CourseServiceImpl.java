package tech.ericwathome.moringaschool.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ericwathome.moringaschool.entity.Course;
import tech.ericwathome.moringaschool.error.CourseNotFoundException;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.TechnicalMentorNotFoundException;
import tech.ericwathome.moringaschool.repository.course.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course addCourse(Course course) throws EmptyParameterException {
        if (course.getName().isEmpty()) throw new EmptyParameterException("can't add an empty course");
        return courseRepository.save(course);
    }

    @Override
    public List<Course> allCourses() {
        Course emptyCourse = Course.builder()
                .name("No courses found")
                .build();
        List<Course> emptyCourseList = new ArrayList<>();
        emptyCourseList.add(emptyCourse);
        List<Course> allCourses = courseRepository.findAll();
        if (allCourses.isEmpty()) {
            return new ArrayList<>(emptyCourseList);
        }
        return allCourses;
    }

    @Override
    public Course findCourseById(Long courseId) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);
        course.orElseThrow(() -> new CourseNotFoundException("NO_COURSE_FOUND_WITH_ID: " + courseId));
        if (course.get().getName().equals("")) {
            throw new CourseNotFoundException("no course found with provided id");
        }
        return course.get();
    }

    @Override
    public Course findCourseByName(String name) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findCourseByNameIgnoreCase(name);
        course.orElseThrow(() -> new CourseNotFoundException("NO_COURSE_FOUND_WITH_NAME: " + name));
        return course.get();
    }

    @Override
    public Course updateCourseById(Long id, Course updatedCourse) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        course.orElseThrow(() -> new CourseNotFoundException("NO_COURSE_FOUND_WITH_ID: " + id));
        if (!updatedCourse.getName().isEmpty()) {
            course.get().setName(updatedCourse.getName());
        }
        courseRepository.save(course.get());
        return course.get();
    }

    @Override
    public Course updateCourseByName(String name, Course updatedCourse) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findCourseByNameIgnoreCase(name);
        course.orElseThrow(() -> new CourseNotFoundException("NO_COURSE_FOUND_WITH_NAME: " + name));
        if (!updatedCourse.getName().isEmpty()) {
            course.get().setName(updatedCourse.getName());
        }
        courseRepository.save(course.get());
        return course.get();
    }

    @Override
    public Course deleteCourseById(Long courseId) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);
        course.orElseThrow(() -> new CourseNotFoundException("NO_COURSE_FOUND_WITH_ID: " + courseId));
        courseRepository.deleteById(courseId);
        return course.get();
    }

    @Override
    public Course deleteCourseByName(String courseName) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findCourseByNameIgnoreCase(courseName);
        course.orElseThrow(() -> new CourseNotFoundException("NO_COURSE_FOUND_WITH_NAME: " + courseName));
        Long id = course.get().getId();
        courseRepository.deleteById(id);
        return course.get();
    }
}

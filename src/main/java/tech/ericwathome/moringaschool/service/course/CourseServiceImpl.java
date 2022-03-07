package tech.ericwathome.moringaschool.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ericwathome.moringaschool.entity.Course;
import tech.ericwathome.moringaschool.error.CourseNotFoundException;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
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
        if (course.get().getName().equals("")) {
            throw new CourseNotFoundException("no course found with provided id");
        }
        return course.get();
    }

    @Override
    public Course findCourseByName(String name) throws CourseNotFoundException {
        Course course = courseRepository.findCourseByNameIgnoreCase(name);
        if (course == null) throw new CourseNotFoundException("no course found with provided name");
        return course;
    }

    @Override
    public Course updateCourseById(Long id, Course updatedCourse) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if (course.get().getName().equals("")) throw new CourseNotFoundException("no course found with provided id");
        if (!updatedCourse.getName().isEmpty()) {
            course.get().setName(updatedCourse.getName());
        }
        courseRepository.save(course.get());
        return course.get();
    }

    @Override
    public Course updateCourseByName(String name, Course updatedCourse) throws CourseNotFoundException {
        Course course = courseRepository.findCourseByNameIgnoreCase(name);
        if (course == null) throw new CourseNotFoundException("no course found with provided name");
        if (!updatedCourse.getName().isEmpty()) {
            course.setName(updatedCourse.getName());
        }
        courseRepository.save(course);
        return course;
    }

    @Override
    public Course deleteCourseById(Long courseId) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.get().getName().equals("")) throw new CourseNotFoundException("no course found with provided id");
        courseRepository.deleteById(courseId);
        return course.get();
    }

    @Override
    public Course deleteCourseByName(String courseName) throws CourseNotFoundException {
        Course course = courseRepository.findCourseByNameIgnoreCase(courseName);
        if (course == null) throw new CourseNotFoundException("no course found with provided name");
        Long id = course.getId();
        courseRepository.deleteById(id);
        return course;
    }
}

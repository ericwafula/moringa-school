package tech.ericwathome.moringaschool.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ericwathome.moringaschool.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseByNameIgnoreCase(String name);
}

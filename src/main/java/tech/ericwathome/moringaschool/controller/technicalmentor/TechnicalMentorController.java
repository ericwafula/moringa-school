package tech.ericwathome.moringaschool.controller.technicalmentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ericwathome.moringaschool.entity.Course;
import tech.ericwathome.moringaschool.entity.TechnicalMentor;
import tech.ericwathome.moringaschool.error.CourseNotFoundException;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.TechnicalMentorNotFoundException;
import tech.ericwathome.moringaschool.service.course.CourseService;
import tech.ericwathome.moringaschool.service.technicalmentor.TechnicalMentorService;

import java.util.List;

@RestController
@RequestMapping("api/v1/technical-mentors")
public class TechnicalMentorController {
    private final TechnicalMentorService technicalMentorService;
    private final CourseService courseService;

    @Autowired
    public TechnicalMentorController(TechnicalMentorService technicalMentorService, CourseService courseService) {
        this.technicalMentorService = technicalMentorService;
        this.courseService = courseService;
    }

    @PostMapping("/technical-mentor/new")
    public TechnicalMentor addTechnicalMentor(@RequestBody TechnicalMentor technicalMentor) throws EmptyParameterException {
        return technicalMentorService.addTechnicalMentor(technicalMentor);
    }

    @GetMapping
    public List<TechnicalMentor> findAllTechnicalMentors() {
        return technicalMentorService.findAllTechnicalMentors();
    }

    @GetMapping("/technical-mentor/id/{id}")
    public TechnicalMentor findTechnicalMentorById(@PathVariable("id") Long id) throws TechnicalMentorNotFoundException {
        return technicalMentorService.findTechnicalMentorById(id);
    }

    @GetMapping("/technical-mentor/name/{name}")
    List<TechnicalMentor> findTechnicalMentorsByCharactersContained(@PathVariable("name") String name) {
        return technicalMentorService.findTechnicalMentorsByContainedCharacters(name);
    }

    @PutMapping("/technical-mentor/id/{id}")
    public TechnicalMentor updateTechnicalMentorById(@PathVariable("id") Long id, @RequestBody TechnicalMentor updatedTechnicalMentor) throws TechnicalMentorNotFoundException {
        return technicalMentorService.updateTechnicalMentorById(id, updatedTechnicalMentor);
    }

    @PutMapping("/technical-mentor/id/{technicalMentorId}/course/id/{courseId}")
    public Course assignCourseToTechnicalMentor(@PathVariable("technicalMentorId") Long technicalMentorId, @PathVariable("courseId") Long courseId) throws TechnicalMentorNotFoundException, CourseNotFoundException {
        TechnicalMentor technicalMentor = technicalMentorService.findTechnicalMentorById(technicalMentorId);
        Course course = courseService.findCourseById(courseId);
        if (technicalMentor == null) throw new TechnicalMentorNotFoundException("No technical mentor found with the provided id");
        if (course == null) {
            throw new CourseNotFoundException("No course found with provided id");
        } else {
            technicalMentor.addCourse(course);
            technicalMentorService.updateTechnicalMentorById(technicalMentorId, technicalMentor);
        }
        return course;
    }

    @DeleteMapping("/technical-mentor/id/{id}")
    public TechnicalMentor deleteTechnicalMentorById(@PathVariable("id") Long id) throws TechnicalMentorNotFoundException {
        return technicalMentorService.deleteTechnicalMentorById(id);
    }
}

package tech.ericwathome.moringaschool.controller.technicalmentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ericwathome.moringaschool.entity.TechnicalMentor;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.TechnicalMentorNotFoundException;
import tech.ericwathome.moringaschool.service.technicalmentor.TechnicalMentorService;

import java.util.List;

@RestController
@RequestMapping("api/v1/technical-mentors")
public class TechnicalMentorController {
    private final TechnicalMentorService technicalMentorService;

    @Autowired
    public TechnicalMentorController(TechnicalMentorService technicalMentorService) {
        this.technicalMentorService = technicalMentorService;
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

    @DeleteMapping("/technical-mentor/id/{id}")
    public TechnicalMentor deleteTechnicalMentorById(@PathVariable("id") Long id) throws TechnicalMentorNotFoundException {
        return technicalMentorService.deleteTechnicalMentorById(id);
    }
}

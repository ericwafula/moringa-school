package tech.ericwathome.moringaschool.service.technicalmentor;

import tech.ericwathome.moringaschool.entity.TechnicalMentor;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.TechnicalMentorNotFoundException;

import java.util.List;

public interface TechnicalMentorService {
    TechnicalMentor addTechnicalMentor(TechnicalMentor technicalMentor) throws EmptyParameterException;

    List<TechnicalMentor> findAllTechnicalMentors();

    TechnicalMentor findTechnicalMentorById(Long id) throws TechnicalMentorNotFoundException;

    List<TechnicalMentor> findTechnicalMentorsByContainedCharacters(String name);

    TechnicalMentor updateTechnicalMentorById(Long id, TechnicalMentor updatedTechnicalMentor) throws TechnicalMentorNotFoundException;

    TechnicalMentor deleteTechnicalMentorById(Long id) throws TechnicalMentorNotFoundException;
}

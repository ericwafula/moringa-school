package tech.ericwathome.moringaschool.service.technicalmentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ericwathome.moringaschool.entity.TechnicalMentor;
import tech.ericwathome.moringaschool.error.EmptyParameterException;
import tech.ericwathome.moringaschool.error.TechnicalMentorNotFoundException;
import tech.ericwathome.moringaschool.repository.technicalmentor.TechnicalMentorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechnicalMentorServiceImpl implements TechnicalMentorService {
    private final TechnicalMentorRepository technicalMentorRepository;

    @Autowired
    public TechnicalMentorServiceImpl(TechnicalMentorRepository technicalMentorRepository) {
        this.technicalMentorRepository = technicalMentorRepository;
    }

    @Override
    public TechnicalMentor addTechnicalMentor(TechnicalMentor technicalMentor) throws EmptyParameterException {
        if (technicalMentor == null) throw new EmptyParameterException("can't save empty technical mentor");
        return technicalMentorRepository.save(technicalMentor);
    }

    @Override
    public List<TechnicalMentor> findAllTechnicalMentors() {
        List<TechnicalMentor> emptyTechnicalMentorsList = new ArrayList<>();
        TechnicalMentor emptyTechnicalMentorMessage = TechnicalMentor.builder()
                .name("No technical mentor found")
                .email("No technical mentor found")
                .build();
        emptyTechnicalMentorsList.add(emptyTechnicalMentorMessage);

        List<TechnicalMentor> allTechnicalMentors = technicalMentorRepository.findAll();
        if (allTechnicalMentors.isEmpty()) {
            return emptyTechnicalMentorsList;
        }
        return allTechnicalMentors;
    }

    @Override
    public TechnicalMentor findTechnicalMentorById(Long id) throws TechnicalMentorNotFoundException {
        Optional<TechnicalMentor> technicalMentor = technicalMentorRepository.findById(id);
        technicalMentor.orElseThrow(() -> new TechnicalMentorNotFoundException("NO_TECHNICAL_MENTOR_FOUND_WITH_ID: " + id));
        return technicalMentor.get();
    }

    @Override
    public List<TechnicalMentor> findTechnicalMentorsByContainedCharacters(String name) {
        List<TechnicalMentor> emptyTechnicalMentorsList = new ArrayList<>();
        TechnicalMentor emptyTechnicalMentorMessage = TechnicalMentor.builder()
                .name("No technical mentor found")
                .email("No technical mentor found")
                .build();
        emptyTechnicalMentorsList.add(emptyTechnicalMentorMessage);

        List<TechnicalMentor> allTechnicalMentors = technicalMentorRepository.findByNameContainsIgnoreCase(name);
        if (allTechnicalMentors.isEmpty()) {
            return emptyTechnicalMentorsList;
        }
        return allTechnicalMentors;
    }

    @Override
    public TechnicalMentor updateTechnicalMentorById(Long id, TechnicalMentor updatedTechnicalMentor) throws TechnicalMentorNotFoundException {
        Optional<TechnicalMentor> technicalMentor = technicalMentorRepository.findById(id);
        technicalMentor.orElseThrow(() -> new TechnicalMentorNotFoundException("NO_TECHNICAL_MENTOR_FOUND_WITH_ID: " + id));
        if (!updatedTechnicalMentor.getName().isEmpty()) {
            technicalMentor.get().setName(updatedTechnicalMentor.getName());
        }
        if (!updatedTechnicalMentor.getEmail().isEmpty()) {
            technicalMentor.get().setEmail(updatedTechnicalMentor.getEmail());
        }
        return technicalMentorRepository.save(technicalMentor.get());
    }

    @Override
    public TechnicalMentor deleteTechnicalMentorById(Long id) throws TechnicalMentorNotFoundException {
        Optional<TechnicalMentor> technicalMentor = technicalMentorRepository.findById(id);
        technicalMentor.orElseThrow(() -> new TechnicalMentorNotFoundException("NO_TECHNICAL_MENTOR_FOUND_WITH_ID: " + id));
        technicalMentorRepository.deleteById(id);
        return technicalMentor.get();
    }
}

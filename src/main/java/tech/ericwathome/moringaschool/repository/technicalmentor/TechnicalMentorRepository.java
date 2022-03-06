package tech.ericwathome.moringaschool.repository.technicalmentor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ericwathome.moringaschool.entity.TechnicalMentor;

import java.util.List;

@Repository
public interface TechnicalMentorRepository extends JpaRepository<TechnicalMentor, Long> {
    List<TechnicalMentor> findByNameContainsIgnoreCase(String name);
}

package tech.ericwathome.moringaschool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "technical_mentor",
        uniqueConstraints = @UniqueConstraint(
                name = "technical_mentor_email_unique",
                columnNames = "technical_mentor_email"
        )
)
public class TechnicalMentor {
    @Id
    @SequenceGenerator(
            name = "technical_mentor_sequence",
            sequenceName = "technical_mentor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "technical_mentor_sequence"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "technical_mentor_name")
    private String name;
    @Column(
            name = "technical_mentor_email",
            nullable = false
    )
    private String email;
}

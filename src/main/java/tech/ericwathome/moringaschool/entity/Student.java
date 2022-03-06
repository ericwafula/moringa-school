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
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "student_email"
                ),
                @UniqueConstraint(
                        name = "admission_number_unique",
                        columnNames = "admission_number"
                )
        }
)
public class Student {
    @Id()
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @Column(name = "student_name")
    private String name;
    @Column(
            name = "student_email",
            nullable = false
    )
    private String email;
    @Column(
            name = "admission_number",
            nullable = false
    )
    private Integer admissionNumber;
}

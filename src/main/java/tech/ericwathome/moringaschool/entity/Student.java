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
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "student_email"
        )
)
public class Student {
    @Id()
    private Long id;
    @Column(name = "student_name")
    private String name;
    @Column(
            name = "student_email",
            nullable = false
    )
    private String email;
}

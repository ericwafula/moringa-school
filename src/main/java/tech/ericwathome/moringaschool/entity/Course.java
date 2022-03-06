package tech.ericwathome.moringaschool.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "course",
        uniqueConstraints = @UniqueConstraint(
                name = "course_name_unique",
                columnNames = "course_name"
        )
)
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(name = "id")
    private Long id;
    @Column(
            name = "course_name",
            nullable = false
    )
    private String name;
    @Column(name = "students")
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "id"
    )
    private List<Student> students;

    public void addStudent(Student student) {
        students.add(student);
    }
}

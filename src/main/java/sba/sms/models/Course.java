package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "course")



/**
 * Course is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'course' in the database. A Course object contains fields that represent course
 * information and a mapping of 'courses' that indicate an inverse or referencing side
 * of the relationship. Implement Lombok annotations to eliminate boilerplate code.
 */
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(length = 50, nullable = false, name = "name")
    public String name;

    @Column(length = 50, nullable = false, name = "instructor")
    public String instructor;

    @Column()
    @ManyToMany (mappedBy = "courses", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,
            CascadeType.REMOVE, CascadeType.MERGE,  CascadeType.PERSIST} )
    public Set<Student> students;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name) && Objects.equals(instructor, course.instructor) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, instructor, students);
    }
}

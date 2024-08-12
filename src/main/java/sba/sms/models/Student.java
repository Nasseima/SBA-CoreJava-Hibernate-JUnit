package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "student")

/**
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bi-directional relationship.
 * Implement Lombok annotations to eliminate boilerplate code.
 */

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @Column(unique = true, length = 50, name = "email")
    private String email;

    @Column(length = 50, nullable = false, name = "name")
    public String name;

    @Column(length = 50, nullable = false, name = "password")
    private String password;

    @JoinTable (name = "student_courses",
            joinColumns = @JoinColumn(name = "student_email"),
    inverseJoinColumns = @JoinColumn(name = "courses_id"))

    @ManyToMany (fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,
            CascadeType.REMOVE, CascadeType.MERGE,  CascadeType.PERSIST} )
    Set<Course> courses = new LinkedHashSet<>();

    public void addCourse(Course c){
        courses.add(c);
        c.getStudents().add(this);

    }


    public Student(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email) && Objects.equals(name, student.name) && Objects.equals(password, student.password) && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password, courses);
    }

}




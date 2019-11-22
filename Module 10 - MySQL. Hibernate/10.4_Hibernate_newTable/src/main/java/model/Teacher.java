package model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int salary;

    private int age;

    @ToString.Exclude
    @OneToMany(mappedBy = "teacher")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Course> courses;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return getName().equals(teacher.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

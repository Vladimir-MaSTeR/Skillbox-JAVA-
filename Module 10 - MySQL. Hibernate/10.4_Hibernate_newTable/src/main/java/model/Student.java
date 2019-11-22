package model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


@Data
@ToString
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private Set<Subscription> subscriptions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getName().equals(student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
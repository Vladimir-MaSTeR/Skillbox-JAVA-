package model;

import lombok.*;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@ToString
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CoursesType type;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "students_count")
    private Integer studentCount;

    private int price;

    @Column(name = "price_per_hour")
    private Float pricePerHour;

    @ToString.Exclude
    @OneToMany(mappedBy = "course")
    private Set<Subscription> subscriptions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getName().equals(course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

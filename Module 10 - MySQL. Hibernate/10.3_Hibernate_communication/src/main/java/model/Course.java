package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    private float pricePerHour;

    @OneToMany(mappedBy = "course")
    private Set<Subscription> subscription;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public CoursesType getType() {
        return type;
    }
    public void setType(CoursesType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getStudentCount() {
        return studentCount;
    }
    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Set<Subscription> getSubscription() {
        return subscription;
    }
    public void setSubscription(Set<Subscription> subscription) {
        this.subscription = subscription;
    }
}

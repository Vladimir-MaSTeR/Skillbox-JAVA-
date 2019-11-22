package model;

import lombok.*;
import lombok.experimental.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@Accessors(chain = true)
@EqualsAndHashCode
@Table(name = "student_courses")
public class StudentsCourses {

    @EmbeddedId
    private Id id;

    @ManyToOne
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

    @Column(name = "price")
    private Integer price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public StudentsCourses(Id id, Student student, Course course, int price, Date subscriptionDate) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }

    @Data
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id implements Serializable {

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;
    }
}

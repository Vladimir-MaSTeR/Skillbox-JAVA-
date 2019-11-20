package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private Id id;

    @ManyToOne
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Embeddable
    @Data
    public static class Id implements Serializable {

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;
    }
}

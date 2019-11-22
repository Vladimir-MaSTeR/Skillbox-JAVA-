package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "purchaselist")
public class PurchaseList {

    @EmbeddedId
    Id id;

    @Column(name = "student_name", updatable = false, insertable = false)
    private String studentName;

    @Column(name = "course_name", updatable = false, insertable = false)
    private String courseName;

    @Column(name = "price")
    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Data
    @Embeddable
    private static class Id implements Serializable {

        @Column(name = "student_name")
        private String student_name;

        @Column(name = "course_name")
        private String course_name;
    }
}

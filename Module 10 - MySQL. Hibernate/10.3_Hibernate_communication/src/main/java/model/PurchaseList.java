package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {

    @Column(name = "student_name", updatable = false, insertable = false)
    private String studentName;

    @Column(name = "course_name", updatable = false, insertable = false)
    private String courseName;

    private  int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @EmbeddedId
    Id id;

    @Embeddable
    private static class Id implements Serializable {

        @Column(name = "student_name")
        private String student_name;

        @Column(name = "course_name")
        private String course_name;



        public String getStudent_name() {
            return student_name;
        }
        public void setStudent_name(String student_name) {
            this.student_name = student_name;
        }

        public String getCourse_name() {
            return course_name;
        }
        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }
    }




    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }
    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Id getId() {
        return id;
    }
    public void setId(Id id) {
        this.id = id;
    }
}

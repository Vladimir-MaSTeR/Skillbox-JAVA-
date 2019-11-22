import model.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       try(SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(PurchaseList.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Subscription.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(StudentsCourses.class)
                .buildSessionFactory()) {

           try (Session session = factory.getCurrentSession()) {
               session.beginTransaction();
               List<Object[]> purchaseLists = session.createQuery("SELECT s.id, c.id, p.price,  p.subscriptionDate " +
                       "FROM PurchaseList p " +
                       "join Student s on (s.name = p.studentName) " +
                       "join Course c on (c.name = p.courseName)", Object[].class).getResultList();

               purchaseLists.stream().forEach(row -> {
                   Student student = session.load(Student.class, (int) row[0]);
                   Course course = session.load(Course.class, (int) row[1]);
                   StudentsCourses studentsCourses = StudentsCourses.builder()
                           .id(new StudentsCourses.Id((int) row[0], (int) row[1]))
                           .student(student)
                           .course(course)
                           .price((int) row[2])
                           .subscriptionDate((Date) row[3])
                           .build();
                   session.saveOrUpdate(studentsCourses);
               });
               session.getTransaction().commit();
           }
       }
    }
}

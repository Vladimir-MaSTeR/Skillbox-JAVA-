import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Set;


public class Main {

    public static void main(String[] args) {
        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(PurchaseList.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Subscription.class)
                .addAnnotatedClass(Teacher.class)
                .buildSessionFactory()) {

            Session session = sessionFactory.openSession();

            Course course = session.get(Course.class, 1);
            Set<Subscription> subscription = course.getSubscription();
            for (Subscription sub : subscription) {
                System.out.println(sub.getStudent().getName());
            }

        }
    }

}

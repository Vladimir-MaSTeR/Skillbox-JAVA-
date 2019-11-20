import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    private static final String NAME_FILE_XML = "hibernate.cfg.xml";
    private static final int ID = 3;

    public static void main(String[] args) {
        Session session = sessionFactory(NAME_FILE_XML).openSession();

        Course course = session.get(Course.class, ID);
        Teachers teachers = session.get(Teachers.class, ID);

        System.out.println("\n   Название курса  из таблицы с Courses * " + course.getName() +
                "\n   Зарплата учителя из таблицы Teachers * " + teachers.getSalary() + "\n");

        sessionFactory(NAME_FILE_XML).close();

    }

    public static SessionFactory sessionFactory (String nameFileXml) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(nameFileXml).build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        return sessionFactory;
    }
}

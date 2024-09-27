import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

//implementation("org.xerial:sqlite-jdbc:3.46.1.0")
//implementation("com.github.gwenn:sqlite-dialect:0.1.4")
//implementation("org.hibernate:hibernate-core:5.5.7.Final")
public class Hibernate implements Storage<HUser, Exception>{

    private final SessionFactory sessionFactory;

    public Hibernate() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC")
                    .setProperty("hibernate.connection.url", "jdbc:sqlite:hibernate.db")
                    .setProperty("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.show_sql", "true");
            configuration.addAnnotatedClass(HUser.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public void save(List<HUser> users) {
        try (Session session = sessionFactory.openSession()) {
            //session.beginTransaction();
            for (var user : users) {
                session.save(user);
            }
            //session.getTransaction().commit();
        }
    }

    public List<HUser> load() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<HUser> query = builder.createQuery(HUser.class);
            Root<HUser> root = query.from(HUser.class);
            query.select(root);
            Query<HUser> q = session.createQuery(query);
            return q.list();
        }
    }


    public static void main(String[] args) throws IOException {
        Files.delete(new File("hibernate.db").toPath());
        var hibenate = new Hibernate();
        System.out.println("Store List<String> in database with hibernate" + User.USERS);
        hibenate.save(User.USERS.stream().map(u->new HUser(u.getId(), u.getName(), u.getAge())).toList());
        System.out.println("Load List<String> from database with hibernate" + hibenate.load());
    }


}
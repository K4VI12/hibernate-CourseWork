package lk.ijse.HostalMangement.config;

import lk.ijse.HostalMangement.entity.ReservationEntity;
import lk.ijse.HostalMangement.entity.RoomEntity;
import lk.ijse.HostalMangement.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SessionFactoryConfig {

    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(StudentEntity.class)
                .addAnnotatedClass(RoomEntity.class)
                .addAnnotatedClass(ReservationEntity.class)
                .buildSessionFactory();

    }

    public static SessionFactoryConfig getInstance() {
        return (factoryConfig==null) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }
    
    public Session getSession() {

        /*Properties properties = new Properties();

        try {
            properties.load(SessionFactoryConfig.class.getResourceAsStream("/hibernatepropertyfile/hibernate.properties"));
        } catch (Exception e) {}
*/
        return sessionFactory.openSession();
    } 

}

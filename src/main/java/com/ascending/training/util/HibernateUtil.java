package com.ascending.training.util;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import java.util.*;


import java.util.Properties;

public class HibernateUtil {
    private  SessionFactory sessionFactory; //singleton pattern

    private Logger logger = LoggerFactory.getLogger(HibernateUtil.class);


    public SessionFactory getSessionFactory()
    {
        if(sessionFactory == null)
        {
            try {
                String[] modelPackages = {"com.ascending.training.model"};
                String dbDriver = System.getProperty("database.driver"); // 这些变量应该通过vm option去配置
                String dbDialect = System.getProperty("database.dialect");
                String dbUrl = System.getProperty("database.url");
                String dbUser = System.getProperty("database.user");
                String dbPassword = System.getProperty("database.password");

                // 不要写solid code  如不要固定url 用户名  密码等等因为可能也会有其他开发者使用该code这样就会造成不便
//            String[] modelPackages = {"com.ascending.training.model"};
//            String dbDriver = "org.postgresql.Driver";
//            String dbDialect = "org.hibernate.dialect.PostgreSQL9Dialect";
//            String dbUrl = "jdbc:postgresql://localhost:5432/dealer";
//            String dbUser = "admin";
//            String dbPassword = "password";


                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, dbDriver);
                settings.put(Environment.DIALECT, dbDialect);
                settings.put(Environment.URL, dbUrl);
                settings.put(Environment.USER, dbUser);
                settings.put(Environment.PASS, dbPassword);

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "validate"); //validate很重要 会把java里面的annotation和数据库里的schema进行验证。 也可以注释掉该语句但推荐使用。
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);

                EntityScanner.scanPackages(modelPackages).addTo(configuration); // 扫描modelPackages中对应的packages中有哪些有annotation


                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder(); // 运用builder design pattern
                ServiceRegistry serviceRegistry = registryBuilder.applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            catch (Exception e)
            {
                logger.error("Fail to generate hibernate sessionfactory", e);
            }

        }
        return sessionFactory;


    }


//    public static void main(String[] args)
//    {
//        SessionFactory sf = HibernateUtil.getSessionFactory();
//        SessionFactory sf2 = HibernateUtil.getSessionFactory();
//        logger.info("success generate sf" + sf.hashCode());
//        logger.info("success generate sf" + sf2.hashCode());
//        Session s = sf.openSession();
//        s.close();
//        Session s1 = sf.openSession();// Factory design pattern 用sf可以创建出两个session实例，此处是该类第二个design pattern
//        s1.close();
//    }




}

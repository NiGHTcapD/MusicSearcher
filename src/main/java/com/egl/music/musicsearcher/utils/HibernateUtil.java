//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.egl.music.musicsearcher.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                StandardServiceRegistry standardRegistry = (new StandardServiceRegistryBuilder()).configure("hibernate.cfg.xml").build();
                Metadata metaData = (new MetadataSources(standardRegistry)).getMetadataBuilder().build();
                sessionFactory = metaData.getSessionFactoryBuilder().build();
            }

            return sessionFactory;
        } catch (Exception var2) {
            throw new ExceptionInInitializerError(var2);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}


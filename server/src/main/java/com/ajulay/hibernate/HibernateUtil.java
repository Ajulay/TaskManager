package com.ajulay.hibernate;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;


public class HibernateUtil {

    @Nullable
    public static SessionFactory factory() throws IOException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        final FileInputStream fis = new FileInputStream(ServiceConstant.HIBERNATE_PROPERTY_ADDRESS);
        final Properties property = new Properties();
        property.load(fis);
        try {
            final Map<String, String> settings = new HashMap<>();
            settings.put(AvailableSettings.DRIVER, property.getProperty(ServiceConstant.HIBERNATE_DRIVER));
            settings.put(AvailableSettings.URL, property.getProperty(ServiceConstant.HIBERNATE_CONNECT));
            settings.put(AvailableSettings.USER, property.getProperty(ServiceConstant.HIBERNATE_USER));
            settings.put(AvailableSettings.PASS, property.getProperty(ServiceConstant.HIBERNATE_PASSWORD));
            settings.put(AvailableSettings.DIALECT, property.getProperty(ServiceConstant.HIBERNATE_DIALECT));
            settings.put(AvailableSettings.HBM2DDL_AUTO, property.getProperty(ServiceConstant.HIBERNATE_HBM2DDL_AUTO));
            settings.put(AvailableSettings.SHOW_SQL, property.getProperty(ServiceConstant.HIBERNATE_SHOW_SQL));
            final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            registryBuilder.applySettings(settings);
            final StandardServiceRegistry registry = registryBuilder.build();
            final MetadataSources sources = new MetadataSources(registry);
            sources.addAnnotatedClass(Task.class);
            sources.addAnnotatedClass(Project.class);
            sources.addAnnotatedClass(Assignee.class);
            sources.addAnnotatedClass(Session.class);
            sources.addAnnotatedClass(User.class);
            sources.addAnnotatedClass(HibernateUtil.class);
            final Metadata metadata = sources.getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApplicationScoped
    @Produces
    @Nullable
    public EntityManager getEntityManager() throws IOException {
        final SessionFactory factory = factory();
        if (factory == null) return null;
        return factory().createEntityManager();
    }

}
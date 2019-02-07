package com.ajulay.hibernate;

import com.ajulay.constants.ServiceConstant;
import com.ajulay.controller.util.PropertyReader;
import com.ajulay.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

public class HibernateUtil {

    @NotNull
    public SessionFactory factory() throws IOException {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        final Properties property = PropertyReader.loadProperty();
            final Map<String, String> settings = new HashMap<>();
            settings.put(AvailableSettings.DRIVER, property.getProperty(ServiceConstant.HIBERNATE_DRIVER));
            settings.put(AvailableSettings.URL, property.getProperty(ServiceConstant.HIBERNATE_CONNECT));
            settings.put(AvailableSettings.USER, property.getProperty(ServiceConstant.HIBERNATE_USER));
            settings.put(AvailableSettings.PASS, property.getProperty(ServiceConstant.HIBERNATE_PASSWORD));
            settings.put(AvailableSettings.DIALECT, property.getProperty(ServiceConstant.HIBERNATE_DIALECT));
            settings.put(AvailableSettings.HBM2DDL_AUTO, property.getProperty(ServiceConstant.HIBERNATE_HBM2DDL_AUTO));
            settings.put(AvailableSettings.SHOW_SQL, property.getProperty(ServiceConstant.HIBERNATE_SHOW_SQL));
        settings.put(AvailableSettings.C3P0_MIN_SIZE, property.getProperty(ServiceConstant.HIBERNATE_C3P0_MIN_SIZE));
        settings.put(AvailableSettings.C3P0_MAX_SIZE, property.getProperty(ServiceConstant.HIBERNATE_C3P0_MAX_SIZE));
        settings.put(AvailableSettings.CONNECTION_PROVIDER, property.getProperty(ServiceConstant.HIBERNATE_CONNECTION_PROVIDER_CLASS));
        settings.put(AvailableSettings.C3P0_ACQUIRE_INCREMENT, property.getProperty(ServiceConstant.HIBERNATE_C3P0_ACQUIRE_INCREMENT));
        settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, property.getProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS));
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
    }

}
package com.shop.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement// Used to enable transactional support: Annotations can be recognized by spring container
@PropertySource(value = {"classpath:database.properties"}) //Externalize your configuration to a properties file
public class DatabaseConfig {

    @Autowired
    private Environment env;//Can be used to read properties file
    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    @Bean
    public DataSource datasource() {
        ComboPooledDataSource datasource = new ComboPooledDataSource();
        try {
            datasource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MyWebMvcConfigurer.class.getName()).log(Level.SEVERE, null, ex);
        }

        datasource.setJdbcUrl(env.getProperty("jdbc.url"));
        datasource.setUser(env.getProperty("jdbc.user"));
        datasource.setPassword(env.getProperty("jdbc.password"));
        logger.log(Level.INFO, ">>>>>>>>jdbc.user={0} logged in", env.getProperty("jdbc.user"));
        datasource.setInitialPoolSize(getIntProperty(env.getProperty("connection.pool.initialPoolSize")));
        datasource.setMinPoolSize(getIntProperty(env.getProperty("connection.pool.minPoolSize")));
        datasource.setMaxPoolSize(getIntProperty(env.getProperty("connection.pool.maxPoolSize")));
        datasource.setMaxIdleTime(getIntProperty(env.getProperty("connection.pool.maxIdleTime")));
        return datasource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasource());
        sessionFactory.setPackagesToScan("com.shop.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private int getIntProperty(String property) {
        int value = Integer.parseInt(property);
        return value;
    }
}

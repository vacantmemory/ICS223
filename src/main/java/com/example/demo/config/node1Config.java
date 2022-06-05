package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan
@EnableJpaRepositories(
        entityManagerFactoryRef = "node1EntityManagerFactory",
        transactionManagerRef = "node1TransactionManager",
        basePackages = { "com.example.demo.Node1DB" }
)
@PropertySource("classpath:application.properties")
public class node1Config {
    @Value("${spring.datasource.url}")
    String Url;

    @Value("${spring.datasource.username}")
    String Username;

    @Value("${spring.datasource.password}")
    String Password;

//    @Autowired
//    private Environment env;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource node1DataSource() {
//        return DataSourceBuilder.create().build();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Url);
        config.setUsername(Username);
        config.setPassword(Password);
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
////        config.addDataSourceProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
////        config.addDataSourceProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
////        config.addDataSourceProperty("spring.jpa.show-sql", "true");
////        config.addDataSourceProperty("spring.jpa.properties.hibernate.format_sql", "true");
        return new HikariDataSource(config);
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean node1EntityManager() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(node1DataSource());
//        em.setPackagesToScan(
//                "com.example.demo.Node1DB");
//
//        HibernateJpaVendorAdapter vendorAdapter
//                = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.ddl-auto",
//                env.getProperty("hibernate.ddl-auto"));
//        properties.put("hibernate.dialect",
//                env.getProperty("hibernate.dialect"));
//        properties.put("show-sql",
//                env.getProperty("show-sql"));
//        properties.put("hibernate.format_sql",
//                env.getProperty("hibernate.format_sql"));
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean node1EntityManagerFactory(@Autowired DataSource node1DataSource) {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(node1DataSource);
        entityManagerFactoryBean.setPackagesToScan("com.example.demo.Node1DB");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        var props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.setProperty("hibernate.show-sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        entityManagerFactoryBean.setJpaProperties(props);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager node1TransactionManager(
            @Autowired EntityManagerFactory node1EntityManagerFactory) {
        return new JpaTransactionManager(node1EntityManagerFactory);
    }

//    @Bean
//    public PlatformTransactionManager node1TransactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(node1EntityManager().getObject());
//        return transactionManager;
//    }
}


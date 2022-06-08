package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan
@EnableJpaRepositories(
        entityManagerFactoryRef = "node3EntityManagerFactory",
        transactionManagerRef = "node3TransactionManager",
        basePackages = {"com.example.demo.Node3DB"}
)
@PropertySource("classpath:application.properties")
public class node3Config {
    @Value("${spring.third-datasource.url}")
    String Url;

    @Value("${spring.third-datasource.username}")
    String Username;

    @Value("${spring.third-datasource.password}")
    String Password;

    @Bean(name = "node3DataSource")
    @ConfigurationProperties(prefix = "spring.third-datasource")
    public DataSource node3DataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Url);
        config.setUsername(Username);
        config.setPassword(Password);
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }

    @Bean(name = "node3EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean node3EntityManagerFactory(@Qualifier("node3DataSource") DataSource node3DataSource) {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(node3DataSource);
        entityManagerFactoryBean.setPackagesToScan("com.example.demo.Node3DB");

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

    @Bean(name = "node3TransactionManager")
    public PlatformTransactionManager node3TransactionManager(
            @Qualifier("node3EntityManagerFactory") EntityManagerFactory node3EntityManagerFactory) {
        return new JpaTransactionManager(node3EntityManagerFactory);
    }
}

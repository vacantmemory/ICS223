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
        entityManagerFactoryRef = "node2EntityManagerFactory",
        transactionManagerRef = "node2TransactionManager",
        basePackages = {"com.example.demo.Node2DB"}
)
@PropertySource("classpath:application.properties")
public class node2Config {
    @Value("${spring.second-datasource.url}")
    String Url;

    @Value("${spring.second-datasource.username}")
    String Username;

    @Value("${spring.second-datasource.password}")
    String Password;


    @Bean(name = "node2DataSource")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource node2DataSource() {
//        return DataSourceBuilder.create().build();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Url);
        config.setUsername(Username);
        config.setPassword(Password);
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }


    @Bean(name = "node2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean node2EntityManagerFactory(@Qualifier("node2DataSource") DataSource node2DataSource) {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(node2DataSource);
        entityManagerFactoryBean.setPackagesToScan("com.example.demo.Node2DB");

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

    @Bean(name = "node2TransactionManager")
    public PlatformTransactionManager node2TransactionManager(
            @Qualifier("node2EntityManagerFactory") EntityManagerFactory node2EntityManagerFactory) {
        return new JpaTransactionManager(node2EntityManagerFactory);
    }
}

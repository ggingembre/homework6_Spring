package com.app.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Main model application configuration. Uses Hibernate for data base management
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.app.dao", "com.app.services"})
@PropertySource("classpath:database.properties")
@EnableJpaRepositories("com.app.dao")
public class ModelConfiguration {

    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.driver}")
    private String driverClass;
    @Value("${db.dialect}")
    private String hibernateDialect;
    @Value("classpath:db.sql")
    private Resource  scriptResource;

    @Bean(destroyMethod = "close")
    public BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setDatabasePlatform(hibernateDialect);

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        bean.setPackagesToScan("com.app.entities");
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }


    // This reads file scriptresources, which is the db.sql file, so it resets the database each time we run this:


    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource){
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(scriptResource);

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }


}
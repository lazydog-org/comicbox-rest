package org.lazydog.comicbox;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Database configuration.
 * 
 * @author rjrjr
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class DatabaseConfiguration {
    
    @Value("${database.batch.size:500}") private int batchSize;
    @Value("${database.fetch.size:1000}") private int fetchSize;
    @Value("${database.hibernate.dialect}") private String hibernateDialect;
    @Value("${database.jndi.name}") private String jndiName;
    
    @Bean(destroyMethod = "")
    public DataSource dataSource() {
        return new JndiDataSourceLookup().getDataSource(this.jndiName);
    }
    
    @Bean
    public Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", this.hibernateDialect);
        jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        jpaProperties.put("hibernate.jdbc.batch_size", this.batchSize);
        jpaProperties.put("hibernate.jdbc.batch_versioned_data", "true");
        jpaProperties.put("hibernate.jdbc.fetch_size", this.fetchSize);
        jpaProperties.put("hibernate.default_batch_fetch_size", this.fetchSize);
        jpaProperties.put("hibernate.order_inserts", "true");
        jpaProperties.put("hibernate.order_updates", "true");
        jpaProperties.put("hibernate.id.new_generator_mappings", "false");
        jpaProperties.put("hibernate.format_sql", "true");
        return jpaProperties;
    }
    
    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource, final Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("org.lazydog.comicbox.model");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }
    
    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(final LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }
}

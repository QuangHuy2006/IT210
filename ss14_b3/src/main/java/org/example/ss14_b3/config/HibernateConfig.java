package org.example.ss14_b3.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.hibernate.HibernateTransactionManager;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {
    //DataSource cung cấp các thông tin như url , username, password
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        // thông tin kết nối đến database
        sessionFactory.setDataSource(dataSource);
        // quét các thực thể để khởi tạo bảng sang database
        sessionFactory.setPackagesToScan("org.example.ss13.model");
        //cấu hình thông số về hibernate dialet , show_sql , format_sql , ddl.auto
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
//            hibernateProperties.put("hibernate.enable_lazy_load_non_transactional", "true");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);

    }

}
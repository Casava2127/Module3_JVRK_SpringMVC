package ra.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
// Đánh dấu lớp này là một lớp cấu hình Spring.

@EnableWebMvc
// Kích hoạt các tính năng MVC của Spring, bao gồm xử lý request, trả về view.

@ComponentScan(basePackages = {"ra.demo.controller","ra.demo.service.imp","ra.demo.repository.imp","ra.demo.mapper"})
// Quét các package được chỉ định để tìm các bean, controller, service, repository và mapper.

@EnableTransactionManagement
// Kích hoạt tính năng quản lý transaction trong Spring.

public class AppConfig {
    // Lớp cấu hình ứng dụng Spring.

    // 1. Cấu hình ViewResolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/");
        // Đường dẫn tiền tố cho view (thư mục chứa các file JSP).

        resolver.setSuffix(".jsp");
        // Đường dẫn hậu tố cho view (định dạng file là `.jsp`).

        return resolver;
        // Trả về một `ViewResolver` để Spring MVC tìm và render view.
    }

    // 2. Cấu hình datasource kết nối với CSDL
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // Cấu hình driver MySQL.

        dataSource.setUrl("jdbc:mysql://localhost:3306/Validation_DB");
        // URL kết nối đến cơ sở dữ liệu MySQL (tên DB là `Validation_DB`).

        dataSource.setUsername("root");
        // Tên người dùng kết nối đến cơ sở dữ liệu.

        dataSource.setPassword("123456");
        // Mật khẩu kết nối đến cơ sở dữ liệu.

        return dataSource;
        // Trả về một `DataSource` để sử dụng trong kết nối cơ sở dữ liệu.
    }

    // 3. Cấu hình hibernate properties
    public Properties addionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        // Hibernate tự động tạo hoặc cập nhật schema trong cơ sở dữ liệu.

        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        // Chỉ định dialect của Hibernate để làm việc với MySQL.

        properties.setProperty("hibernate.show_sql", "true");
        // Hiển thị các câu lệnh SQL trong console để kiểm tra.

        return properties;
        // Trả về các thuộc tính cấu hình Hibernate.
    }

    // 4. Cấu hình EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        // Kết nối `EntityManagerFactory` với `DataSource`.

        entityManagerFactory.setPackagesToScan("ra.demo.model");
        // Quét các package chứa các thực thể (Entity) của JPA.

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        // Sử dụng Hibernate làm nhà cung cấp JPA.

        entityManagerFactory.setJpaProperties(addionalProperties());
        // Gán các thuộc tính cấu hình Hibernate.

        return entityManagerFactory;
        // Trả về `EntityManagerFactory` để quản lý các thực thể JPA.
    }

    // 5. Cấu hình EntityManager
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
        // Trả về một `EntityManager` để tương tác với cơ sở dữ liệu.
    }

    // 6. Cấu hình transaction
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        // Gán `EntityManagerFactory` cho transaction manager.

        return transactionManager;
        // Trả về một `PlatformTransactionManager` để quản lý các giao dịch.
    }
}

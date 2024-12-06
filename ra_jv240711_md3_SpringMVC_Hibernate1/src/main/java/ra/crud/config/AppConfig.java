package ra.crud.config; // Định nghĩa package chứa file cấu hình Spring này

// Import các thư viện cần thiết từ Spring Framework và Java
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


//File này là trung tâm cấu hình cho ứng dụng Spring MVC.

// Đánh dấu lớp này là một lớp cấu hình Spring thay cho file XML (app-context.xml)
@Configuration

// Bật tính năng Spring MVC, hỗ trợ xử lý request và trả về response
@EnableWebMvc

// Cấu hình tự động quét các component (Controller, Service, Repository, ...) trong các package được chỉ định
@ComponentScan(basePackages = {"ra.crud.controller", "ra.crud.service.imp", "ra.crud.repository.imp"})

// Kích hoạt quản lý giao dịch (Transaction Management) cho các hoạt động liên quan đến cơ sở dữ liệu
@EnableTransactionManagement
public class AppConfig {

    // 1. Cấu hình ViewResolver: Phân giải tên view để tìm file JSP tương ứng
    @Bean
    public ViewResolver viewResolver() {
        // InternalResourceViewResolver giúp tìm các file JSP trong thư mục chỉ định
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views/"); // Thư mục gốc chứa các file JSP
        resolver.setSuffix(".jsp");   // Đuôi mở rộng của các file JSP
        return resolver; // Trả về ViewResolver để Spring MVC sử dụng
    }

    // 2. Cấu hình DataSource: Kết nối ứng dụng với cơ sở dữ liệu MySQL
    @Bean
    public DataSource dataSource() {
        // DriverManagerDataSource là một implementation của DataSource để kết nối cơ sở dữ liệu
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Driver MySQL JDBC
        dataSource.setUrl("jdbc:mysql://localhost:3306/EmployeeManager"); // URL của cơ sở dữ liệu
        dataSource.setUsername("root"); // Tên người dùng cơ sở dữ liệu
        dataSource.setPassword("123456"); // Mật khẩu cơ sở dữ liệu
        return dataSource; // Trả về DataSource để ứng dụng sử dụng
    }

    // 3. Cấu hình Hibernate Properties: Thiết lập các thuộc tính Hibernate cho ORM
    public Properties addionalProperties() {
        Properties properties = new Properties(); // Tạo đối tượng Properties để chứa các thuộc tính
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Tự động cập nhật schema (tạo bảng nếu chưa có)
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8InnoDBDialect");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Dialect dành cho MySQL
        properties.setProperty("hibernate.show_sql", "true"); // Hiển thị câu SQL trong log để debug
        return properties; // Trả về danh sách các thuộc tính
    }

    // 4. Cấu hình EntityManagerFactory: Factory để quản lý các Entity và ánh xạ ORM
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource()); // Gán DataSource đã cấu hình
        entityManagerFactory.setPackagesToScan("ra.crud.model"); // Chỉ định package chứa các Entity (model)
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); // Hibernate làm JPA Provider
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter); // Gắn JPA Vendor Adapter cho Factory
        entityManagerFactory.setJpaProperties(addionalProperties()); // Cấu hình thêm các thuộc tính Hibernate
        return entityManagerFactory; // Trả về EntityManagerFactory để Spring quản lý
    }

    // 5. Cấu hình EntityManager: Đối tượng quản lý các Entity trong runtime
    @Bean
    @Qualifier(value = "entityManager") // Đặt tên định danh cho bean này
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager(); // Tạo và trả về EntityManager từ EntityManagerFactory
    }

    // 6. Cấu hình Transaction Manager: Quản lý các giao dịch (transaction) - phien lam viec trong ứng dụng
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(); // Sử dụng JPA Transaction Manager
        transactionManager.setEntityManagerFactory(entityManagerFactory); // Gắn EntityManagerFactory
        return transactionManager; // Trả về Transaction Manager để Spring quản lý giao dịch
    }
}

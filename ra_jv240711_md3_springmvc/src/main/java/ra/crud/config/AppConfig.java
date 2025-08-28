package ra.crud.config;
// cau hinh spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
// Đánh dấu lớp này là một lớp cấu hình trong Spring Framework, duoc Spring containerq quan ly va tao bean
//Tương đương với file XML trong cấu hình Spring truyền thống.
@EnableWebMvc //KICH HOAT các tính năng của Spring MVC, như xử lý yêu cầu HTTP, ánh xạ URL, và hỗ trợ RESTful API.
@ComponentScan(basePackages = {"ra.crud.controller", "ra.crud.repository.imp", "ra.crud.service.imp"})// Chỉ định các package mà Spring sẽ quét để tìm các bean hoặc thành phần như @Controller, @Service, @Repository.
public class AppConfig {
    //Cấu hình ViewResolver
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //set đường dẫn đến thư mục chứa các view
        viewResolver.setPrefix("/views/");
        //set loại view (.jsp)
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
//ích hoạt Spring MVC và quét các package để tìm thành phần Spring.
//Cấu hình ViewResolver để liên kết controller với file giao diện .jsp.
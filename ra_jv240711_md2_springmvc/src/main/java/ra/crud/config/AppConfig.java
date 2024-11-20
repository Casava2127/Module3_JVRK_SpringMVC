package ra.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ra.crud.controller", "ra.crud.repository.imp", "ra.crud.service.imp"})
public class AppConfig {
    //Cấu hình ViewResolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //set đường dẫn đến thư mục chứa các view
        viewResolver.setPrefix("/views/");
        //set loại view (.jsp)
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

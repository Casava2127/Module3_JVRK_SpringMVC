package ra.crud.config;
//Module3/SpringMVC_CRUD
// Định nghĩa package của lớp WebConfig, giúp tổ chức các lớp trong dự án
// DispatcherServlet
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
// Nhập khẩu lớp AbstractAnnotationConfigDispatcherServletInitializer từ Spring Framework,
// đây là lớp mà bạn kế thừa để cấu hình ứng dụng Spring MVC

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    // Lớp WebConfig kế thừa từ AbstractAnnotationConfigDispatcherServletInitializer để cấu hình Spring MVC ma khogn can su dung web.xml
    // khi ke thua lop na, cos the de dang cau hinh cac thanh phan spring mvc bang cach ghi de phuong thuc

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Phương thức này được ghi đè để trả về các lớp cấu hình root của ứng dụng
        return new Class[0]; // Trả về một mảng trống vì trong trường hợp này không sử dụng root config
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Phương thức này được ghi đè để trả về các lớp cấu hình servlet của ứng dụng
        return new Class[]{AppConfig.class};
        // Trả về lớp AppConfig, nơi cấu hình các bean và các thành phần của ứng dụng web
    }

    @Override
    protected String[] getServletMappings() {
        // Phương thức này được ghi đè để trả về các mapping URL cho DispatcherServlet
        return new String[]{"/"};
        // DispatcherServlet sẽ được ánh xạ tới tất cả các URL ("/"), có nghĩa là mọi yêu cầu HTTP sẽ được xử lý bởi DispatcherServlet
    }
}



//Đăng ký DispatcherServlet cho ứng dụng.
//Liên kết DispatcherServlet với lớp cấu hình AppConfig.
//Định nghĩa các URL mà DispatcherServlet sẽ tiếp nhận.
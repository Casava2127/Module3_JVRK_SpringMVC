package ra.demo.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloundiaryConfig {
    private static final String CLOUND_NAME = "dtig9dlxu";
    private static final String API_KEY = "246997171399652";
    private static final String API_SECRET = "LQOxzx2HXva0dbtFrQbtl2tLMh0";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUND_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET
        ));
    }
}

package khiemvuong.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Tạo bean RestTemplate để sử dụng trong toàn bộ ứng dụng
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}

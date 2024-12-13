package com.nttdata.pruebatecnicabdb.configurations;
import com.nttdata.pruebatecnicabdb.mappings.ApplicationConfigMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${server.port}") // Inject server port from properties
    private static String serverPort;

    private static final String LOCALHOST = "http://localhost:%s".formatted(CorsConfig.serverPort);
    private final ApplicationConfigMapper configMapper;

    public CorsConfig(ApplicationConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String allowedOrigin = configMapper.ALLOWED_ORIGIN;
        if (allowedOrigin == null || allowedOrigin.isEmpty()){
            allowedOrigin = "*";
        }
        registry.addMapping("/api/**") // Apply to specific paths
                .allowedOrigins(LOCALHOST, allowedOrigin) // Allowed origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                .allowedHeaders("*") // Allowed headers
                .allowCredentials(true); // Allow sending credentials (cookies, Authorization header)
    }
}

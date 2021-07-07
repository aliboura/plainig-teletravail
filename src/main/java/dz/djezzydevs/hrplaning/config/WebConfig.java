package dz.djezzydevs.hrplaning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
                //            .allowedOrigins("https://networkapp.djezzy.dz")
               // .allowedMethods("*")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
              //  .exposedHeaders("*")
                .allowCredentials(false);
    }
}

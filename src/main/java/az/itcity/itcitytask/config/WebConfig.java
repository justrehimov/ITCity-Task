package az.itcity.itcitytask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${root.path}")
    private String rootPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = "file:///" + rootPath + "/src/main/resources/upload/";
        registry.addResourceHandler("/**")
                .addResourceLocations(path);
    }
}

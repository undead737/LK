package ru.ofd.lk.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ru.ofd.lk.security.config.SecurityConfig;

@Configuration
@EnableWebMvc
@ComponentScan({ "ru.ofd.lk.configurations", "ru.ofd.lk.controllers" })
@Import({ SecurityConfig.class })
public class WebConfig {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/components/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

package ru.gontarenko.tacojdbc.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Можно заменить классы (Контроллеры), которые не содержат логики и просто отдают view
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
    }
}

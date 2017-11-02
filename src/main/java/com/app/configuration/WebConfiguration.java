package com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Spring MVC configuration used to configure all kind of resources.
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.app.controllers"})
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // http://localhost:8080/jpeg/face.jpeg -> /WEB-INF/jpegs/face.jpeg
        registry.addResourceHandler("/jpeg/**").addResourceLocations("/WEB-INF/jpegs/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/*.html").addResourceLocations("/WEB-INF/html/");
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // View -> RedirectView
        registry.addRedirectViewController("/", "/product/showAll");
        registry.addRedirectViewController("/user", "/user/show");
        registry.addRedirectViewController("/product", "/product/showAll");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

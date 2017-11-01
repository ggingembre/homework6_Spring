package com.app.applications;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.app.configuration.ModelConfiguration;
import com.app.configuration.SpringSecurityConfiguration;
import com.app.configuration.WebConfiguration;

/**
 * Main entry point to Spring MVC application.
 *
 * Also it register {@link org.springframework.web.servlet.DispatcherServlet} that handle
 * all requests.
 */
public class WebMvcApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {ModelConfiguration.class, SpringSecurityConfiguration.class} ;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

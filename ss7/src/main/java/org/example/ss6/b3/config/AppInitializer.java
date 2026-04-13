package org.example.ss6.b3.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        MultipartConfigElement multipleConfigElement = new MultipartConfigElement("C:/RikkeiFood_Temp/", 5* 1024 * 1024, 10 * 1024 * 1024, 0);
        registration.setMultipartConfig(multipleConfigElement);
    }

    @Override
    protected Class<?> @Nullable [] getRootConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected Class<?> @Nullable [] getServletConfigClasses() {
        return new Class[0];
    }
}

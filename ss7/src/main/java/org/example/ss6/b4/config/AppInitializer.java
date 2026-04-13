package org.example.ss6.b4.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;

public class AppInitializer {
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        MultipartConfigElement multipleConfigElement = new MultipartConfigElement("C:/RikkeiFood_Temp/", 2 * 1024 * 1024, 2 * 1024 * 1024, 0);
        registration.setMultipartConfig(multipleConfigElement);
    }
}

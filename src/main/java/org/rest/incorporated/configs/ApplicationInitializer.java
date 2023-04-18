package org.rest.incorporated.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootContainerConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DispatcherChildConfigs.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/api/*"};
    }
}

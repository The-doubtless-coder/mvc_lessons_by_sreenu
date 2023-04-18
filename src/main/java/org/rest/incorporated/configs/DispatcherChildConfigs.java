package org.rest.incorporated.configs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"org.rest.incorporated.controller", "org.rest.incorporated.exceptions"})
//set up a filter to avoid service components and configs
public class DispatcherChildConfigs implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET", "POST");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("/img");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
                objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
//                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            }
            if(converter instanceof  MappingJackson2XmlHttpMessageConverter){
                MappingJackson2XmlHttpMessageConverter xmlHttpMessageConverter = (MappingJackson2XmlHttpMessageConverter)converter;
                xmlHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
                ObjectMapper objectMapper1 = xmlHttpMessageConverter.getObjectMapper();
                objectMapper1.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            }
            }
        }
        private List<MediaType> getSupportedMediaTypes(){
        List<MediaType> forXml = new ArrayList<MediaType>();
        forXml.add(MediaType.APPLICATION_JSON);
        forXml.add(MediaType.APPLICATION_XML);
        return forXml;
    }
    @Bean("swaggerBean")
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.rest.incorporated.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    public ApiInfo getApiInfo(){
        return new ApiInfo("Spring Mvc Tests With SreenuTech",
                "About Dispatcher and all Mvc Components",
                "2.0","http://", "BackendWizard",
                "Http://"
                ,".mightyWizard");
    }

}

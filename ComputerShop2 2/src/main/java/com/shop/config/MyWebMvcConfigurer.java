package com.shop.config;

import com.shop.converter.StringToCategoryConverter;
import com.shop.converter.StringToRoleConverter;
//import com.shop.converter.StringToRoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.shop")
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private StringToRoleConverter stringToRoleConverter;
    @Autowired
    private StringToCategoryConverter stringToCategoryConverter;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String[] paths = {"/static/**"};
        String[] locations = {"/static/"};
        registry.addResourceHandler(paths).addResourceLocations(locations);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToRoleConverter);
        registry.addConverter(stringToCategoryConverter);
    }

    //gia to upload // tou apatche to upload https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/multipart-resolver.html
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }
}

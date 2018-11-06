package com.example.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@EnableCaching
public class AdminApplication {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return (container ->{
            ErrorPage error401page=new ErrorPage(HttpStatus.UNAUTHORIZED,"/401.html");
            ErrorPage error404page=new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
            ErrorPage error500page=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500.html");
            container.addErrorPages(error401page,error404page,error500page);
        });
    }


    public static void main(String[] args) throws IOException {
        Properties properties=new Properties();
        InputStream in=AdminApplication.class.getClassLoader().getResourceAsStream("admin.properties");
        properties.load(in);
        SpringApplication app=new SpringApplication(AdminApplication.class);
        app.setDefaultProperties(properties);
        app.run(args);
    }
}

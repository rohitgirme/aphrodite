package com.memories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rohitgirme on 1/10/16.
 */

// Spring Boot detects spring-webmvc on the classpath. This flags the
// application as a web application and activates key behaviors
// such as setting up a DispatcherServlet to handle the REST calls.
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
    return application.sources(Application.class);
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }
}

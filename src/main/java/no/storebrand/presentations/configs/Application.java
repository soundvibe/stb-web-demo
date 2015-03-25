package no.storebrand.presentations.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author OZY on 2015.03.23
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"no.storebrand.presentations.web.controllers"})
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}

package com.project.ticketmntsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.ticketmntsys.contoller.AdminContoller;
import com.project.ticketmntsys.entity.User;

@SpringBootApplication
public class TicketmanagementsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketmanagementsystemApplication.class, args);
		
		

		
		System.out.println("project stared");
	}
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedMethods("*").allowedOrigins("http://localhost:3000");
	            }
	        };

}


}


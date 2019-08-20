package com.example.bgoug;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class BgougApplication {

    public static void main(String[] args) {
        SpringApplication.run(BgougApplication.class, args);
//        System.out.println(org.hibernate.Version.getVersionString());
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    
	
//	  @Bean CharacterEncodingFilter characterEncodingFilter() { 
//	  CharacterEncodingFilter filter = new CharacterEncodingFilter(); 
//	  filter.setEncoding("UTF-8"); 
//	  filter.setForceEncoding(true); 
//	  return filter; 
//	  }
	 
//    @Bean
//    CharacterEncodingFilter characterEncodingFilter() {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        return filter;
//    }
}

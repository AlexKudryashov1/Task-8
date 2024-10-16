package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class Mapper {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

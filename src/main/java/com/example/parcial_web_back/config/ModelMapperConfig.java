package com.example.parcial_web_back.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Custom converter for LocalDate
        modelMapper.addConverter(context -> {
            String source = context.getSource();
            if (source == null) {
                return null;
            }
            try {
                return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                throw new RuntimeException("Error al convertir fecha: " + source, e);
            }
        }, String.class, LocalDate.class);

        modelMapper.addConverter(context -> {
            LocalDate source = context.getSource();
            if (source == null) {
                return null;
            }
            return source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }, LocalDate.class, String.class);

        return modelMapper;
    }
}

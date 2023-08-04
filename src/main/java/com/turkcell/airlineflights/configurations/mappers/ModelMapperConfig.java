package com.turkcell.airlineflights.configurations.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //bu anotasyon ile bean araması gerçekleşir
public class ModelMapperConfig
{
    @Bean //metot olduğğu için bean yazıyoruz
    public ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }
}

package com.turkcell.airlineflights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AirlineFlightsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AirlineFlightsApplication.class, args);

    }
}
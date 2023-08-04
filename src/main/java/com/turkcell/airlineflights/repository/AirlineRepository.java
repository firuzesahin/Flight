package com.turkcell.airlineflights.repository;

import com.turkcell.airlineflights.entities.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Integer>
{

}
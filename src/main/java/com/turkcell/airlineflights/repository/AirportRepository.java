package com.turkcell.airlineflights.repository;

import com.turkcell.airlineflights.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer>
{

}
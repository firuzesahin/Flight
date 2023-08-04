package com.turkcell.airlineflights.repository;

import com.turkcell.airlineflights.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer>
{

}
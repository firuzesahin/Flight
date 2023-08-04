package com.turkcell.airlineflights.business.dto.responses.update;

import com.turkcell.airlineflights.entities.Airline;
import com.turkcell.airlineflights.entities.Airport;
import java.time.LocalDateTime;

public class UpdateFlightResponse
{
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Airline airline;
    private Airport airportS;
    private Airport airportE;
}
package com.turkcell.airlineflights.business.dto.responses.get;


import java.time.LocalDateTime;

public class GetFlightResponse
{
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int airlineID;
    private int airportSID;
    private int airportEID;
}
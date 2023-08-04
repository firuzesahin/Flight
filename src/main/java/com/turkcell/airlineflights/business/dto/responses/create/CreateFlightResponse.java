package com.turkcell.airlineflights.business.dto.responses.create;

import java.time.LocalDateTime;

public class CreateFlightResponse
{
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int airlineID;
    private int airportSID;
    private int airportEID;
}
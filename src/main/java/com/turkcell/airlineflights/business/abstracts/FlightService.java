package com.turkcell.airlineflights.business.abstracts;

import com.turkcell.airlineflights.business.dto.requests.create.CreateFlightRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateFlightRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateFlightResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllFlightsResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetFlightResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateFlightResponse;

import java.util.List;

public interface FlightService
{
    GetFlightResponse getById(int id);
    List<GetAllFlightsResponse> getAll();
    CreateFlightResponse add(CreateFlightRequest request);
    UpdateFlightResponse update(int id, UpdateFlightRequest request);
    void delete(int id);
}
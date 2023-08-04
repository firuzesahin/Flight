package com.turkcell.airlineflights.business.abstracts;

import com.turkcell.airlineflights.business.dto.requests.create.CreateAirportRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateAirlineRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateAirlineResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAirlineResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllAirlinesResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateAirlineResponse;

import java.util.List;

public interface AirlineService
{
    GetAirlineResponse getById(int id);
    List<GetAllAirlinesResponse> getAll();
    CreateAirlineResponse add(CreateAirportRequest request);
    UpdateAirlineResponse update(int id, UpdateAirlineRequest request);
    void delete(int id);
}
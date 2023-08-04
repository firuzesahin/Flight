package com.turkcell.airlineflights.business.abstracts;


import com.turkcell.airlineflights.business.dto.requests.create.CreateAirportRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateAirportRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateAirportResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAirportResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllAirportsResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateAirportResponse;

import java.util.List;

public interface AirportService
{
    GetAirportResponse getById(int id);
    List<GetAllAirportsResponse> getAll();
    CreateAirportResponse add(CreateAirportRequest request);
    UpdateAirportResponse update(int id, UpdateAirportRequest request);
    void delete(int id);


}
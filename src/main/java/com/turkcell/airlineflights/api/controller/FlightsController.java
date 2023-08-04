package com.turkcell.airlineflights.api.controller;

import com.turkcell.airlineflights.business.abstracts.FlightService;
import com.turkcell.airlineflights.business.dto.requests.create.CreateFlightRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateFlightRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateFlightResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllFlightsResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetFlightResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateFlightResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/flights")
public class FlightsController
{
    private final FlightService service;

    @GetMapping("/{id}")
    GetFlightResponse getById(@PathVariable int id)
    {
        return service.getById(id);
    }

    @GetMapping
    List<GetAllFlightsResponse> getAll()
    {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateFlightResponse add(@Validated @RequestBody CreateFlightRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    UpdateFlightResponse update(@PathVariable int id,@RequestBody UpdateFlightRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(int id)
    {
        service.delete(id);
    }
}
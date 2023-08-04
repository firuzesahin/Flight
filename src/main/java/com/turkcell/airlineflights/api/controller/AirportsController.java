package com.turkcell.airlineflights.api.controller;

import com.turkcell.airlineflights.business.abstracts.AirportService;
import com.turkcell.airlineflights.business.dto.requests.create.CreateAirportRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateAirportRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateAirportResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAirportResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllAirportsResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateAirportResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/airports")
public class AirportsController
{
    private final AirportService service;

    @GetMapping("/{id}")
    GetAirportResponse getById(@PathVariable int id)
    {
        return service.getById(id);
    }

    @GetMapping
    List<GetAllAirportsResponse> getAll()
    {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateAirportResponse add(@Validated @RequestBody CreateAirportRequest request)
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    UpdateAirportResponse update(@PathVariable int id,@RequestBody UpdateAirportRequest request)
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
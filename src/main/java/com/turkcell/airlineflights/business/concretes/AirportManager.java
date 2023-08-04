package com.turkcell.airlineflights.business.concretes;

import com.turkcell.airlineflights.business.abstracts.AirportService;
import com.turkcell.airlineflights.business.dto.requests.create.CreateAirportRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateAirportRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateAirportResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAirportResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllAirportsResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateAirportResponse;
import com.turkcell.airlineflights.entities.Airport;
import com.turkcell.airlineflights.repository.AirportRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportManager implements AirportService
{
    private AirportRepository repository;
    private final ModelMapper mapper;
    @Override
    public GetAirportResponse getById(int id)
    {
        Airport  airport = repository.findById(id).orElseThrow();
        GetAirportResponse response = mapper.map(airport, GetAirportResponse.class);

        return response;
    }

    @Override
    @Cacheable(value = "airport_list")
    public List<GetAllAirportsResponse> getAll()
    {
        List<Airport> airports = repository.findAll();
        List<GetAllAirportsResponse> response = airports
                .stream()
                .map(airport -> mapper.map(airport, GetAllAirportsResponse.class))
                .toList();

        return response;
    }

    @Override
    @CacheEvict(value = "airport_list", allEntries = true)
    public CreateAirportResponse add(CreateAirportRequest request)
    {
        Airport airport = mapper.map(request, Airport.class);
        airport.setId(0);
        repository.save(airport);
        CreateAirportResponse response = mapper.map(airport, CreateAirportResponse.class);

        return response;
    }

    @Override
    @CacheEvict(value = "airport_list", allEntries = true)
    public UpdateAirportResponse update(int id, UpdateAirportRequest request)
    {
        Airport airport = mapper.map(request, Airport.class);
        airport.setId(id);
        repository.save(airport);
        UpdateAirportResponse response = mapper.map(airport, UpdateAirportResponse.class);

        return response;
    }

    @Override
    @CacheEvict(value = "airport_list", allEntries = true)
    public void delete(int id)
    {
        repository.deleteById(id);
    }

}
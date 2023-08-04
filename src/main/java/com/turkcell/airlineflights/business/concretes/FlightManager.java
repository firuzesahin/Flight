package com.turkcell.airlineflights.business.concretes;

import com.turkcell.airlineflights.business.abstracts.FlightService;
import com.turkcell.airlineflights.business.dto.requests.create.CreateFlightRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateFlightRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateFlightResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllFlightsResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetFlightResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateFlightResponse;
import com.turkcell.airlineflights.entities.Flight;
import com.turkcell.airlineflights.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightManager implements FlightService
{
    private final FlightRepository repository;
    private final ModelMapper mapper;

    @Override
    public GetFlightResponse getById(int id)
    {
        Flight flight = repository.findById(id).orElseThrow();
        GetFlightResponse response = mapper.map(flight, GetFlightResponse.class);

        return response;
    }

    @Override
    @Cacheable(value = "flight_list")
    public List<GetAllFlightsResponse> getAll()
    {
        List<Flight> flights = repository.findAll();
        List<GetAllFlightsResponse> response = flights
                .stream()
                .map(flight -> mapper.map(flight, GetAllFlightsResponse.class))
                .toList();

        return response;
    }

    @Override
    @CacheEvict(value = "flight_list", allEntries = true)
    public CreateFlightResponse add(CreateFlightRequest request)
    {
        Flight flight = mapper.map(request, Flight.class);
        flight.setId(0);
        repository.save(flight);
        CreateFlightResponse response = mapper.map(flight, CreateFlightResponse.class);

        return response;
    }

    @Override
    @CacheEvict(value = "flight_list", allEntries = true)
    public UpdateFlightResponse update(int id, UpdateFlightRequest request)
    {
        Flight flight = mapper.map(request, Flight.class);
        flight.setId(id);
        repository.save(flight);
        UpdateFlightResponse response = mapper.map(flight, UpdateFlightResponse.class);

        return response;
    }

    @Override
    @CacheEvict(value = "flight_list", allEntries = true)
    public void delete(int id)
    {
        repository.deleteById(id);
    }
}
package com.turkcell.airlineflights.business.concretes;

import com.turkcell.airlineflights.business.abstracts.AirlineService;
import com.turkcell.airlineflights.business.dto.requests.create.CreateAirportRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateAirlineRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateAirlineResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAirlineResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllAirlinesResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateAirlineResponse;
import com.turkcell.airlineflights.entities.Airline;
import com.turkcell.airlineflights.repository.AirlineRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

//Bir veritabanıyla etkileşim kurma veya harici API’leri çağırma işlemleri.
@Service
@AllArgsConstructor
public class AirlineManager implements AirlineService
{
    private final AirlineRepository repository;
    private final ModelMapper mapper; //Bir objeden başka bir objeye veri dönüşümü için ekliyoruz.

    @Override
    public GetAirlineResponse getById(int id)
    {
        Airline airline = repository.findById(id).orElseThrow();
        GetAirlineResponse response = mapper.map(airline, GetAirlineResponse.class);

        return response;
    }

    @Override
    @Cacheable(value = "airline_list") //İlk çağrıldığında veri tabanından, ikinci defa çağrıldığında önbellekten gelir.
    public List<GetAllAirlinesResponse> getAll()
    {
        List<Airline> airlines = repository.findAll();
        List<GetAllAirlinesResponse> response = airlines
                .stream()
                .map(airline -> mapper.map(airline, GetAllAirlinesResponse.class))
                .toList();

        return response;
    }

    @Override
    @CacheEvict(value = "airline_list", allEntries = true) //Önbellekte ilgili key ile yazılmış değer varsa onu siler.
    public CreateAirlineResponse add(CreateAirportRequest request)
    {
        Airline airline = mapper.map(request, Airline.class);
        airline.setId(0);
        repository.save(airline);
        CreateAirlineResponse response = mapper.map(airline, CreateAirlineResponse.class);

        return response;
    }

    @Override
    @CacheEvict(value = "airline_list", allEntries = true)
    public UpdateAirlineResponse update(int id, UpdateAirlineRequest request)
    {
        Airline airline = mapper.map(request, Airline.class);
        airline.setId(id);
        repository.save(airline);
        UpdateAirlineResponse response = mapper.map(airline, UpdateAirlineResponse.class);

        return response;
    }

    @Override
    @CacheEvict(value = "airline_list", allEntries = true)
    public void delete(int id)
    {
        repository.deleteById(id);
    }
}
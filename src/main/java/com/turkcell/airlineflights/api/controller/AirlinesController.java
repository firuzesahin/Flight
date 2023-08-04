package com.turkcell.airlineflights.api.controller;

import com.turkcell.airlineflights.business.abstracts.AirlineService;
import com.turkcell.airlineflights.business.dto.requests.create.CreateAirportRequest;
import com.turkcell.airlineflights.business.dto.requests.update.UpdateAirlineRequest;
import com.turkcell.airlineflights.business.dto.responses.create.CreateAirlineResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAirlineResponse;
import com.turkcell.airlineflights.business.dto.responses.get.GetAllAirlinesResponse;
import com.turkcell.airlineflights.business.dto.responses.update.UpdateAirlineResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestController, Controller ve ResponseBody’nin birleşimidir. xml ve json dönüşü yapar.
//RequestMapping, belirtilen URL ile ilgili tüm işleri yapması sağlanır.
@RestController
@AllArgsConstructor
@RequestMapping("/api/airlines")
public class AirlinesController
{
    private final AirlineService service;

    @GetMapping("/{id}")
    GetAirlineResponse getById(@PathVariable int id) //URL'deki değişkenleri metoda aktarır.
    {
        return service.getById(id);
    }

    @GetMapping
    List<GetAllAirlinesResponse> getAll()
    {
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Tarayıcı ile sunucu arasındaki aktarım işlemidir. İstemci sunucuda bir şey oluşturmak istedi.
    CreateAirlineResponse add(@Validated @RequestBody CreateAirportRequest request) ////Şartların kontrolü ve body’nin içerik türünü tanımlamak için kullanılır.
    {
        return service.add(request);
    }

    @PutMapping("/{id}")
    UpdateAirlineResponse update(@PathVariable int id,@RequestBody UpdateAirlineRequest request)
    {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Geriye dönecek bir içerik yok.
    void delete(int id)
    {
        service.delete(id);
    }
}
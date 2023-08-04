package com.turkcell.airlineflights.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight")
public class Flight
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline; //Bir havayolu firmasının, birden fazla uçuşu olabilir.

    @ManyToOne
    @JoinColumn(name = "airportS_id")
    private Airport airportS; //Bir havaalanında, birden fazla uçuş olabilir.

    @ManyToOne
    @JoinColumn(name = "airportE_id")
    private Airport airportE; //Bir havaalanında, birden fazla uçuş olabilir.
}
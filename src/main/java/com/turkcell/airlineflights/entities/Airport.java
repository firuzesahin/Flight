package com.turkcell.airlineflights.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airport")
public class Airport
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int code;
    private String city;
    private String name;

    @OneToMany(mappedBy = "airportS", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Flight> flightsS;

    @OneToMany(mappedBy = "airportE", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Flight> flightsE;
}
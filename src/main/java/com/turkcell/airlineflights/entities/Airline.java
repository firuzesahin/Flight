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
@Table(name = "airline")
public class Airline
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int code;
    private String name;
    private int nft = 0;

    @OneToMany(mappedBy = "airline", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Flight> flights;
}
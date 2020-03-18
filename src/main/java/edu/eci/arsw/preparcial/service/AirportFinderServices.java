package edu.eci.arsw.preparcial.service;

import edu.eci.arsw.preparcial.model.Airport;

import java.util.List;

public interface AirportFinderServices {

    List<Airport> getAirportsByName(String name);
}

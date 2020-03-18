package edu.eci.arsw.preparcial.cache;

import edu.eci.arsw.preparcial.model.Airport;

import java.time.LocalDateTime;
import java.util.List;

public interface AirportsFinderCache {
    List<Airport> getAirportByName(String name);
    void saveAirport(String name, List<Airport> airport);
    LocalDateTime getTime(String name);
}

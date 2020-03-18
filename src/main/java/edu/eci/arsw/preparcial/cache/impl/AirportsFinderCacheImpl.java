package edu.eci.arsw.preparcial.cache.impl;

import edu.eci.arsw.preparcial.cache.AirportsFinderCache;
import edu.eci.arsw.preparcial.model.Airport;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AirportsFinderCacheImpl implements AirportsFinderCache {

    private ConcurrentHashMap<String, List<Airport>> airports;
    private ConcurrentHashMap<String,LocalDateTime> time;

    public AirportsFinderCacheImpl(){
        airports = new ConcurrentHashMap<>();
        time = new ConcurrentHashMap<>();
    }

    @Override
    public List<Airport> getAirportByName(String name) {
        return airports.get(name);
    }

    @Override
    public void saveAirport(String name, List<Airport> airport) {
        airports.put(name,airport);
        time.put(name, LocalDateTime.now());
}
    @Override
    public LocalDateTime getTime(String name){
        return time.get(name);
    }
}

package edu.eci.arsw.preparcial.controller;

import edu.eci.arsw.preparcial.model.Airport;
import edu.eci.arsw.preparcial.service.AirportFinderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AirportsFinderController {

    @Autowired
    private AirportFinderServices airportFinderServices;


    @GetMapping("/airports/{name}")
    public ResponseEntity<?> getAirportsByName(@PathVariable(name = "name") String name){
        List<Airport> airportListData = null;
        try{
            airportListData = airportFinderServices.getAirportsByName(name);
            return new ResponseEntity<>(airportListData, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package edu.eci.arsw.preparcial.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.eci.arsw.preparcial.cache.AirportsFinderCache;
import edu.eci.arsw.preparcial.model.Airport;
import edu.eci.arsw.preparcial.service.AirportFinderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirportFinderServicesImpl implements AirportFinderServices {
    private String headerHost;
    private String headerHostValue;
    private String headerKey;
    private String headerKeyValue;
    private String url;
    private Gson gson;
    @Autowired
    private AirportsFinderCache airportsFinderCache;
    public AirportFinderServicesImpl(){
        url = "https://cometari-airportsfinder-v1.p.rapidapi.com/api/";
        headerHost = "x-rapidapi-host";
        headerHostValue = "cometari-airportsfinder-v1.p.rapidapi.com";
        headerKey = "x-rapidapi-key";
        headerKeyValue = "ecd92ce78fmsha41e1754a0b09e1p1b709bjsna4f3e9e91296";
        gson = new GsonBuilder().create();
    }


    @Override
    public List<Airport> getAirportsByName(String name){
        String encodedUrlName = null;
        try {
            encodedUrlName = URLEncoder.encode(name,java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder apiUrl = new StringBuilder();
        apiUrl.append(url);
        apiUrl.append("airports/by-text?text="+encodedUrlName);

        HttpResponse<String> apiResponse = null;
        try{
            apiResponse = Unirest.get(apiUrl.toString())
                    .header(headerHost,headerHostValue)
                    .header(headerKey,headerKeyValue)
                    .asString();
        }catch (UnirestException e){
            e.printStackTrace();
        }
        List<Airport> res = null;
        if(airportsFinderCache.getAirportByName(name) == null){
            System.out.println("no esta en cache");
            res = gson.fromJson(apiResponse.getBody(),new TypeToken<List<Airport>>(){}.getType());
            airportsFinderCache.saveAirport(name,res);
        }else{
            LocalDateTime time = airportsFinderCache.getTime(name);
            if(LocalDateTime.now().isAfter(time.plusMinutes(5))){
                System.out.println("cache 5 minutos");
                res = gson.fromJson(apiResponse.getBody(),new TypeToken<List<Airport>>(){}.getType());
                airportsFinderCache.saveAirport(name,res);
            }else{
                System.out.println("esta en cache");
                res = airportsFinderCache.getAirportByName(name);
            }
        }
        return res;
    }
}

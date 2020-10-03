/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m4herosightings.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 *
 * @author irabob
 */
public class Geocode {
    private static final String GEOCODING_RESOURCE = "https://geocode.search.hereapi.com/v1/geocode";
    private static final String API_KEY = "3P6XH99Cx9r4e-OzHdUu0N80hjX5Kj0TnXC6wIT5SHM";
    
       public String GeocodeSync(String query) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        
        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(query,"UTF-8");
        String requestUri = GEOCODING_RESOURCE + "?apiKey=" + API_KEY + "&q=" + encodedQuery;

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse geocodingResponse = httpClient.send(geocodingRequest,
                HttpResponse.BodyHandlers.ofString());

        String responseStr = (String) geocodingResponse.body();
        
        JsonNode responseJsonNode = mapper.readTree(responseStr);
        JsonNode items = responseJsonNode.get("items");
        
        String result="";
        for (JsonNode item : items) {
            JsonNode address = item.get("address");
            JsonNode position = item.get("position");

            String lat = position.get("lat").asText();
            String lng = position.get("lng").asText();
            result=lat + "," + lng;
        }
        
        return result;
        
        }
}

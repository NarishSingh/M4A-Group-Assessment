package com.sg.m4herosightings.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Geocode {

    private static final String GEOCODING_RESOURCE = "https://geocode.search.hereapi.com/v1/geocode";
    private static final String API_KEY = "3P6XH99Cx9r4e-OzHdUu0N80hjX5Kj0TnXC6wIT5SHM";

    /**
     * Use the HERE API to convert an address to coordinates
     *
     * @param query {String} a valid address
     * @return {String} the latitude and longitude of the address passed in
     * @throws IOException          for invalid queries
     * @throws InterruptedException if http request times out
     */
    public String GeocodeSync(String query) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(query, "UTF-8");
        String requestUri = GEOCODING_RESOURCE + "?apiKey=" + API_KEY + "&q=" + encodedQuery;

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET()
                .uri(URI.create(requestUri)).timeout(Duration.ofMillis(2000)).build();

        HttpResponse geocodingResponse = httpClient.send(geocodingRequest,
                HttpResponse.BodyHandlers.ofString());

        String responseStr = (String) geocodingResponse.body();

        JsonNode responseJsonNode = mapper.readTree(responseStr);
        JsonNode items = responseJsonNode.get("items");

        String result = "";
        for (JsonNode item : items) {
            JsonNode address = item.get("address");
            JsonNode position = item.get("position");

            String lat = position.get("lat").asText();
            String lng = position.get("lng").asText();
            result = lat + "," + lng;
        }

        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.LocationDao;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.helper.Geocode;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author irabob
 */

@Controller
public class LocationController {
    
    @Autowired
    LocationDao locationDao;
    
    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    
    @GetMapping("location")
    public String getLocations(Model model){
        List<Location> locations = locationDao.readAllLocations();
        model.addAttribute("locations", locations);
        return "location";
    }
    
    @PostMapping("addLocation")
    public String addLocation(Location location){
        //assign location info to get coordinates
        String address = location.getStreet() + ", " + location.getCity() + 
                         ", " + location.getState() + " " + location.getZipcode();
        
        //Geocode converter object
        Geocode geo = new Geocode();
        
        try{
            //spliting coordinates by comma 
            String[] coordinates = geo.GeocodeSync(address).split(",");
            //assigning latitude to location
            location.setLatitude(Double.parseDouble(coordinates[0]));
            //assigning longitude to location
            location.setLongitude(Double.parseDouble(coordinates[1]));
        }catch(IOException | InterruptedException ex){
            //incase exception set to 0.0
            location.setLatitude(0.0);
            location.setLongitude(0.0);
        }
        
        locationDao.createLocation(location);
        return "redirect:/location";
    }
    
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id){
        locationDao.deleteLocationById(id);
        return "redirect:/location";
    }
    
    @GetMapping("showLocationDetails")
    public String showDetails(Integer id, Model model){
        Location location = locationDao.readLocationById(id);
        model.addAttribute("location", location);
        return "location";
    }
    
    @PostMapping("updateLocation")
    public String updateLocation(Location location){
        String address = location.getStreet() + ", " + location.getCity() + 
                         ", " + location.getState() + " " + location.getZipcode();
        
        //Geocode converter object
        Geocode geo = new Geocode();
        
        try{
            //spliting coordinates by comma 
            String[] coordinates = geo.GeocodeSync(address).split(",");
            //assigning latitude to location
            location.setLatitude(Double.parseDouble(coordinates[0]));
            //assigning longitude to location
            location.setLongitude(Double.parseDouble(coordinates[1]));
        }catch(IOException | InterruptedException ex){
            //incase exception set to 0.0
            location.setLatitude(0.0);
            location.setLongitude(0.0);
        }
        locationDao.updateLocation(location);
        return "redirect:/location";
    }
    
}

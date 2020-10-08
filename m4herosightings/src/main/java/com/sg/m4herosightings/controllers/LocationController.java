package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.Geocode;
import com.sg.m4herosightings.dao.LocationDao;
import com.sg.m4herosightings.dto.Location;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {

    @Autowired
    LocationDao locationDao;

    Set<ConstraintViolation<Location>> violations = new HashSet<>();

    /**
     * GET - load all locations from db
     *
     * @param model {Model}
     * @return {String} the main subdomain
     */
    @GetMapping("location")
    public String getLocations(Model model) {
        List<Location> locations = locationDao.readAllLocations();
        model.addAttribute("locations", locations);
        model.addAttribute("errors", violations);

        return "location";
    }
    
    /**
     * POST - add a new location to db
     *
     * @param location {Location} constructed from user inputs
     * @return {String} redirect to subdomain
     */
    @PostMapping("addLocation")
    public String addLocation(Location location) {
        //assign location info to get coordinates
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);
        if (violations.isEmpty()) {
            String address = location.getStreet() + ", " + location.getCity()
                    + ", " + location.getState() + " " + location.getZipcode();

            //Geocode converter object
            Geocode geo = new Geocode();

            try {
                //spliting coordinates by comma 
                String[] coordinates = geo.GeocodeSync(address).split(",");
                //assigning latitude to location
                location.setLatitude(Double.parseDouble(coordinates[0]));
                //assigning longitude to location
                location.setLongitude(Double.parseDouble(coordinates[1]));
                locationDao.createLocation(location);
            } catch (IOException | NumberFormatException |InterruptedException ex) {
                //incase exception set to 0.0
                location.setLatitude(0.0);
                location.setLongitude(0.0);
            }
            
            
        }

        return "redirect:/location";
    }

    /**
     * GET - delete a location from db
     *
     * @param id {Integer} id for an existing location, retrieved from template
     * @return {String} redirect to subdomain
     */
    @GetMapping("deleteLocation")
    public String deleteLocation(Integer id) {
        locationDao.deleteLocationById(id);

        return "redirect:/location";
    }

    /**
     * GET - view location information
     *
     * @param id    {Integer} id for an existing location, retrieved from
     *              template
     * @param model {Model}
     * @return {String} the main subdomain
     */
    @GetMapping("showLocationDetails")
    public String showDetails(Integer id, Model model) {
        Location location = locationDao.readLocationById(id);
        model.addAttribute("location", location);

        return "locationDetails";
    }
    
    /**
     * GET - location info to edit form
     * @param id location id
     * @param model for location object
     * @return to updateLocation
     */
    @GetMapping("editLocation")
    public String updateLocation(Integer id, Model model){
        Location location = locationDao.readLocationById(id);
        model.addAttribute("location", location);
        return "editLocation";
    }

    /**
     * POST - update a location in db
     *
     * @param location {Location} a well formed obj with id corresponding to the
     *                 obj to be edited
     * @return {String} redirect to subdomain
     */
    @PostMapping("editLocation")
    public String updateLocation(@Valid Location location, BindingResult result) {
        
        if(result.hasErrors()){
            return "editLocation";
        }
        
        
        String address = location.getStreet() + ", " + location.getCity()
                + ", " + location.getState() + " " + location.getZipcode();

        //Geocode converter object
        Geocode geo = new Geocode();

        try {
            //spliting coordinates by comma 
            String[] coordinates = geo.GeocodeSync(address).split(",");
            //assigning latitude to location
            location.setLatitude(Double.parseDouble(coordinates[0]));
            //assigning longitude to location
            location.setLongitude(Double.parseDouble(coordinates[1]));
            locationDao.updateLocation(location);
        } catch (IOException | NumberFormatException| InterruptedException ex) {
            //incase exception set to 0.0
            location.setLatitude(0.0);
            location.setLongitude(0.0);
        }


        return "redirect:/location";
    }

}
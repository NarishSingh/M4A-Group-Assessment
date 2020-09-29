package com.sg.m4herosightings.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

public class Location {
    /*fields*/
    private int locationId;
    
    @NotBlank(message = "Latitude cannot be blank")
    @Digits(integer = 2, fraction = 6, message = "Please enter a valid latitude")
    private double latitude;
    
    @NotBlank(message = "Longitude cannot be blank")
    @Digits(integer = 3, fraction = 6, message = "Please enter a valid longitude")
    private double longitude;
    
    //TODO add address obj if we end up keeping this
}

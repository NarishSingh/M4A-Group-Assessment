package com.sg.m4herosightings.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Sighting {
    
    /*fields*/
    private int sightingId;
    
    @NotBlank(message = "Sighting date cannot be blank")
    @Past(message = "Future sightings cannot be confirmed")
    private LocalDate date;
    
    @NotBlank(message = "Please describe your hero/villian encounter or sighting")
    @Size(max = 255, message = "Please describe your sighting in 255 chars or less")
    private String description;
    
    private Hero hero;
    
    private Location location; //TODO incomplete as of now
    
    /*ctors*/
    public Sighting() {
    }

    public Sighting(LocalDate date, String description, Hero hero, Location location) {
        this.date = date;
        this.description = description;
        this.hero = hero;
        this.location = location;
    }
    
    public Sighting(int sightingId, LocalDate date, String description, Hero hero, Location location) {
        this.sightingId = sightingId;
        this.date = date;
        this.description = description;
        this.hero = hero;
        this.location = location;
    }
    
    /*g/s*/
    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    /*testing*/
    //TODO make testing methods after fixing the location DTO issues
    
}

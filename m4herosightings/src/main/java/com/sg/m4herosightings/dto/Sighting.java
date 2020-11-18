package com.sg.m4herosightings.dto;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

public class Sighting {

    /*fields*/
    private int sightingId;

    @NotNull(message = "Sighting date must be in the past")
    @PastOrPresent(message = "Future sightings cannot be confirmed")
    private LocalDate date;

    @NotBlank(message = "Please describe your hero/villian encounter or sighting")
    @Size(max = 255, message = "Please describe your sighting in 255 chars or less")
    private String description;

    private Hero hero;
    private Location location;

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
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.sightingId;
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.hero);
        hash = 47 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.hero, other.hero)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sighting{" + "sightingId=" + sightingId + ", date=" + date
                + ", description=" + description + ", hero=" + hero + ", location="
                + location + '}';
    }

}

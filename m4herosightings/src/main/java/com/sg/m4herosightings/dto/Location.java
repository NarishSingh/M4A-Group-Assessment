package com.sg.m4herosightings.dto;

import java.util.Objects;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Location {

    /*fields*/
    private int locationId;

    //@NotBlank(message = "Latitude cannot be blank")
    @Digits(integer = 2, fraction = 6, message = "Please enter a valid latitude")
    private double latitude;

    //@NotBlank(message = "Longitude cannot be blank")
    @Digits(integer = 3, fraction = 6, message = "Please enter a valid longitude")
    private double longitude;

    @Size(max = 50, message = "Location name cannot exceed 50 chars")
    private String name;

    @Size(max = 255, message = "Location description cannot exceed 255 chars")
    private String description;

    @NotBlank(message = "Street must not be blank")
    @Size(max = 100, message = "Street address cannot exceed 100 chars")
    private String street;

    @NotBlank(message = "City must not be blank")
    @Size(max = 30, message = "City name cannot exceed 30 chars")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(max = 2, message = "Please enter the state abbreviation")
    private String state;

    @NotBlank(message = "Zipcode must not be blank")
    @Size(max = 5, message = "Please enter a valid zipcode")
    private String zipcode;

    /*ctors*/
    public Location() {
    }

    public Location(double latitude, double longitude, String name, String description, 
            String street, String city, String state, String zipcode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Location(int locationId, double latitude, double longitude, String name, 
            String description, String street, String city, String state, String zipcode) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    /*g/s*/
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /*testing*/
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.locationId;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.street);
        hash = 83 * hash + Objects.hashCode(this.city);
        hash = 83 * hash + Objects.hashCode(this.state);
        hash = 83 * hash + Objects.hashCode(this.zipcode);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zipcode, other.zipcode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Location{" + "locationId=" + locationId + ", latitude=" + latitude
                + ", longitude=" + longitude + ", name=" + name + ", description="
                + description + ", street=" + street + ", city=" + city + ", state="
                + state + ", zipcode=" + zipcode + '}';
    }

}

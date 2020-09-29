package com.sg.m4herosightings.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Superpower {

    /*fields*/
    private int superpowerId;

    @NotBlank(message = "Superpower name cannot be blank")
    @Size(max = 50, message = "Superpower name must be fewer than 50 chars")
    private String name;

    @NotBlank(message = "Please describe this superpower")
    @Size(max = 255, message = "Superpower descrption must be fewer than 255 chars")
    private String description;

    /*ctors*/
    public Superpower() {
    }

    public Superpower(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Superpower(int superpowerId, String name, String description) {
        this.superpowerId = superpowerId;
        this.name = name;
        this.description = description;
    }

    /*get/set*/
    public int getSuperpowerId() {
        return superpowerId;
    }

    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
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

    /*testing*/
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.superpowerId;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.description);
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
        final Superpower other = (Superpower) obj;
        if (this.superpowerId != other.superpowerId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Superpower{" + "superpowerId=" + superpowerId + ", name=" + name
                + ", description=" + description + '}';
    }

}

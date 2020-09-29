package com.sg.m4herosightings.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Hero {

    /*fields*/
    private int heroId;

    @NotBlank(message = "Hero name cannot be blank")
    @Size(max = 50, message = "Hero name cannot be longer than 50 chars")
    private String name;

    @NotBlank(message = "Please describe this hero or villian")
    @Size(max = 50, message = "Hero/Villian description cannot be longer than 255 chars")
    private String description;

    private Superpower superpower;

    /*ctor*/
    public Hero() {
    }

    public Hero(String name, String description, Superpower superpower) {
        this.name = name;
        this.description = description;
        this.superpower = superpower;
    }

    public Hero(int heroId, String name, String description, Superpower superpower) {
        this.heroId = heroId;
        this.name = name;
        this.description = description;
        this.superpower = superpower;
    }

    /*g/s*/
    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
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

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

    /*testing*/
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.heroId;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.superpower);
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
        final Hero other = (Hero) obj;
        if (this.heroId != other.heroId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superpower, other.superpower)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hero{" + "heroId=" + heroId + ", name=" + name + ", description=" 
                + description + ", superpower=" + superpower + '}';
    }

}

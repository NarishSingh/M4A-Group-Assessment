package com.sg.m4herosightings.dto;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Organization {

    /*fields*/
    private int organizationId;

    @NotBlank(message = "Organization name cannot be blank")
    @Size(max = 50, message = "Organization name cannot exceed 50 chars")
    private String name;

    @Size(max = 255, message = "Please describe the organization in 255 chars or less")
    private String description;

    @Size(max = 12, message = "Please enter a valid US phone number")
    private String phone;

    @Size(max = 50)
    @Email(message = "Please enter a valid email address")
    private String email;

    private Location location;
    
    @Size(min = 1, message = "Please choose at least one member")
    private List<Hero> members = new ArrayList<>();

    /*ctor*/
    public Organization() {
    }

    public Organization(String name, String description, String phone, String email, Location location) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.location = location;
    }

    public Organization(int organizationId, String name, String description, String phone, String email, Location location) {
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.location = location;
    }

    /*g/s*/
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Hero> getMembers() {
        return members;
    }

    public void setMembers(List<Hero> members) {
        this.members = members;
    }

    /*testing*/
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.organizationId;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.phone);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.location);
        hash = 59 * hash + Objects.hashCode(this.members);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.members, other.members)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Organization{" + "organizationId=" + organizationId + ", name="
                + name + ", description=" + description + ", phone=" + phone
                + ", email=" + email + ", location=" + location + ", members="
                + members + '}';
    }

}

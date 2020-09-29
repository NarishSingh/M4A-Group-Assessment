package com.sg.m4herosightings.dto;

import java.util.List;
import java.util.ArrayList;
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

    private List<Hero> members = new ArrayList<>();

    //TODO add contact obj if we end up keeping that
    //TODO add address or location obj based on feedback
}

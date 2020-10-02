package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.HeroDao;
import com.sg.m4herosightings.dao.LocationDao;
import com.sg.m4herosightings.dao.OrganizationDao;
import com.sg.m4herosightings.dao.SightingDao;
import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Hero;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HeroController {
    
    @Autowired
    SuperpowerDao spDao;
    
    @Autowired
    HeroDao hDao;
    
    @Autowired
    OrganizationDao oDao;
    
    @Autowired
    LocationDao ldao;
    
    @Autowired
    SightingDao siDao;
    
    Set<ConstraintViolation<Hero>> violations = new HashSet<>(); 
}

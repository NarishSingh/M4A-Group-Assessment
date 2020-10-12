package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.HeroDao;
import com.sg.m4herosightings.dao.LocationDao;
import com.sg.m4herosightings.dao.OrganizationDao;
import com.sg.m4herosightings.dao.SightingDao;
import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Sighting;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    SuperpowerDao spDao;
    @Autowired
    HeroDao hDao;
    @Autowired
    LocationDao locDao;
    @Autowired
    SightingDao siDao;
    @Autowired
    OrganizationDao oDao;

    /**
     * GET - load 10 latest sightings for newsfeed
     *
     * @param model {Model} holds sorted list of 10 latest sightings
     * @return {String} load homepage
     */
    @GetMapping("/")
    public String displayLatest10Sightings(Model model) {
        List<Sighting> allSi = siDao.readAllSightings();

        List<Sighting> sightings = allSi.stream()
                .sorted(Comparator.comparing(Sighting::getDate).reversed())
                .limit(10)
                .collect(Collectors.toList());

        model.addAttribute("sightings", sightings);

        return "index";
    }
}

package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.HeroDao;
import com.sg.m4herosightings.dao.LocationDao;
import com.sg.m4herosightings.dao.OrganizationDao;
import com.sg.m4herosightings.dao.SightingDao;
import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Sighting;
import com.sg.m4herosightings.dto.Superpower;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
public class SightingController {

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
    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();

    /**
     * GET - on loading subdomain, load all sightings from db and render to page
     *
     * @param model {Model} will hold lists of sightings and their related obj
     *              lists
     * @return {String} load subdomain
     */
    @GetMapping("sighting")
    public String displaySightings(Model model) {
        List<Superpower> superpowers = spDao.readAllSuperpowers();
        List<Hero> heroes = hDao.readAllHeroes();
        List<Location> locations = locDao.readAllLocations();
        List<Sighting> sightings = siDao.readAllSightings();

        model.addAttribute("superpowers", superpowers);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        model.addAttribute("sightings", sightings);

        model.addAttribute("errors", violations);

        return "sighting";
    }

    /*CREATE*/
    /**
     * POST - create a new sighting in db
     *
     * @param request {HttpServletRequest}
     * @return {String} reload page if errors, redirect to Sighting homepage if
     *         successful
     */
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        String dateString = request.getParameter("date");
        String heroId = request.getParameter("heroId");
        String descriptionString = request.getParameter("description");
        String locationId = request.getParameter("locationId");

        Sighting sighting = new Sighting();
        if (!dateString.isBlank()) {
            sighting.setDate(LocalDate.parse(dateString));
            sighting.setHero(hDao.readHeroById(Integer.parseInt(heroId)));
            sighting.setDescription(descriptionString);
            sighting.setLocation(locDao.readLocationById(Integer.parseInt(locationId)));
        }

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sighting);

        if (violations.isEmpty()) {
            siDao.createSighting(sighting);
        }

        return "redirect:/sighting";
    }

    /*VIEW DETAILS*/
    /**
     * GET - view Sighting details
     *
     * @param id    {Integer} a valid id retrieved from template
     * @param model {Model} will hold the retrieved Hero obj
     * @return {String} load page with obj
     */
    @GetMapping("viewSighting")
    public String viewSightingDetails(Integer id, Model model) {
        Sighting sighting = siDao.readSightingById(id);

        model.addAttribute("sighting", sighting);
        model.addAttribute("hero", sighting.getHero());
        model.addAttribute("location", sighting.getLocation());

        return "viewSighting";
    }

    /*EDIT*/
    /**
     * GET - attempt to edit a Sighting
     *
     * @param id    {Integer} a valid id for an existing sighting
     * @param model {Model} will hold related lists for editing a Sighting
     * @return {String} load page with the original
     */
    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) {
        List<Hero> heroes = hDao.readAllHeroes();
        model.addAttribute("heroes", heroes);

        List<Location> locations = locDao.readAllLocations();
        model.addAttribute("locations", locations);

        Sighting sighting = siDao.readSightingById(id);
        model.addAttribute("sighting", sighting);

        model.addAttribute("errors", violations);

        return "editSighting";
    }

    /*
    @PostMapping("editSighting")
    public String performEditSighting(@Valid Sighting sighting, BindingResult result, 
//            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            HttpServletRequest request, Model model) {
        String heroId = request.getParameter("heroId");
        String locationId = request.getParameter("locationId");
        sighting.setHero(hDao.readHeroById(Integer.parseInt(heroId)));
        sighting.setLocation(locDao.readLocationById(Integer.parseInt(locationId)));
        
        String dateString = request.getParameter("date");
        if (!dateString.isEmpty()) {
            sighting.setDate(LocalDate.parse(dateString));
        } else {
            FieldError error = new FieldError("sighting", "date", "Please choose a valid date in the past");
            result.addError(error);
        }

        if (result.hasErrors()) {
            model.addAttribute("sighting", sighting);
            
            model.addAttribute("superpowers", spDao.readAllSuperpowers());
            model.addAttribute("heroes", hDao.readAllHeroes());
            model.addAttribute("locations", locDao.readAllLocations());
            model.addAttribute("errors", violations);

            return "editSighting";
        }

        siDao.updateSighting(sighting);

        return "redirect:/sighting";
    }
     */
    /**
     * POST - perform an update on a sighting
     *
     * @param request
     * @param date
     * @param model
     * @return {String} redirect to home page if successful, reload with errors
     *         if fails
     */
    @PostMapping("editSighting")
    public String performEditSighting(HttpServletRequest request,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        Sighting sighting = siDao.readSightingById(Integer.parseInt(request.getParameter("id")));

        String heroId = request.getParameter("heroId");
        String locationId = request.getParameter("locationId");
        String descrString = request.getParameter("description");

        sighting.setDate(date);
        sighting.setDescription(descrString);
        sighting.setHero(hDao.readHeroById(Integer.parseInt(heroId)));
        sighting.setLocation(locDao.readLocationById(Integer.parseInt(locationId)));

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sighting);

        if (violations.isEmpty()) {
            siDao.updateSighting(sighting);
        } else {
            model.addAttribute("sighting", sighting);

            model.addAttribute("superpowers", spDao.readAllSuperpowers());
            model.addAttribute("heroes", hDao.readAllHeroes());
            model.addAttribute("locations", locDao.readAllLocations());
            model.addAttribute("errors", violations);

            return "editSighting";
        }

        return "redirect:/sighting";
    }

    /*DELETE*/
    /**
     * GET - delete a Sighting
     *
     * @param id {Integer} a valid if for a sighting in db
     * @return {String} redirect to sighting homepage
     */
    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        siDao.deleteSightingById(id);

        return "redirect:/sighting";
    }
}

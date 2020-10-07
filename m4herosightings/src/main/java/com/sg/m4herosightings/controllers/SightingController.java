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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

        return "sighting";
    }

    /*CREATE*/
    /**
     * POST - create a new sighting in db
     *
     * @param sighting {Sighting} a valid obj
     * @param result   {BindingResult} will hold validation errors
     * @param request  {HttpServletRequest}
     * @param model    {Model} will hold the lists required for creating
     *                 Sighting
     * @return {String} reload page if errors, redirect to Sighting homepage if
     *         successful
     */
    @PostMapping("addSighting")
    public String addSighting(@Valid Sighting sighting, BindingResult result,
            HttpServletRequest request, Model model) {
        String heroId = request.getParameter("heroId");
        String locationId = request.getParameter("locationId");

        if (heroId == null) {
            FieldError error = new FieldError("hero", "heroId", "Must include a hero or villian sighted");
            result.addError(error);
        } else if (locationId == null) {
            FieldError error = new FieldError("location", "locationId", "Must include a location");
            result.addError(error);
        } else {
            sighting.setHero(hDao.readHeroById(Integer.parseInt(heroId)));
            sighting.setLocation(locDao.readLocationById(Integer.parseInt(locationId)));
        }

        if (result.hasErrors()) {
            model.addAttribute("superpowers", spDao.readAllSuperpowers());
            model.addAttribute("heroes", hDao.readAllHeroes());
            model.addAttribute("locations", locDao.readAllLocations());

            return "addSighting";
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
        List<Location> locations = locDao.readAllLocations();
        Sighting sighting = siDao.readSightingById(id);

        model.addAttribute("heroes", heroes);
        model.addAttribute("location", locations);
        model.addAttribute("sighting", sighting);

        return "editSighting";
    }

    /**
     * POST - perform the edit of a Sighting obj
     *
     * @param sighting {Sighting} obj to be validated
     * @param result   {BindingResult} holds validation errors for Sighting
     * @return {String} reload page if fail from errors, return to sighting
     *         homepage if successfully updates
     */
    @PostMapping("editSighting")
    public String performEditSighting(@Valid Sighting sighting, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("superpowers", spDao.readAllSuperpowers());
            model.addAttribute("heroes", hDao.readAllHeroes());
            model.addAttribute("locations", locDao.readAllLocations());

            model.addAttribute("sighting", sighting);
            sighting.setDate(LocalDate.parse(dateString));
            sighting.setHero(hDao.readHeroById(Integer.parseInt(heroId)));
            sighting.setLocation(locDao.readLocationById(Integer.parseInt(locationId)));
          
            return "editSighting";
        }

        siDao.updateSighting(sighting);

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

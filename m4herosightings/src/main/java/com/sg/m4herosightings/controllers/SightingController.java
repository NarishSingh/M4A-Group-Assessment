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
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param request {HttpServletRequest} will retrieve form data for new
     *                sighting
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

    /**
     * POST - perform an update on a sighting
     *
     * @param request {HttpServletRequest} retrieves form data for attempting
     *                update
     * @param date    {LocalDate} a formatted date from the past
     * @param model   {Model} holds obj on reload for failure to edit
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
     * GET - load delete confirmation page for a sighting from db
     *
     * @param request {HttpServletRequest} will pull in id to retrieve obj
     * @param model   {Model} will hold relevant data for sighting
     * @return {String} load page with sighting to be deleted
     */
    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting sighting = siDao.readSightingById(id);

        model.addAttribute("sighting", sighting);
        model.addAttribute("hero", sighting.getHero());
        model.addAttribute("location", sighting.getLocation());

        return "deleteSighting";
    }

    /**
     * GET - delete a sighting from db
     *
     * @param request {HttpServletRequest} will pull in id for delete query
     * @return {String} redirect to sighting homepage
     */
    @GetMapping("performDeleteSighting")
    public String performDeleteSighting(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        siDao.deleteSightingById(id);

        return "redirect:/sighting";
    }
}

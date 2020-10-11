package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.HeroDao;
import com.sg.m4herosightings.dao.LocationDao;
import com.sg.m4herosightings.dao.OrganizationDao;
import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Organization;
import java.util.ArrayList;
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
public class OrganizationController {

    @Autowired
    OrganizationDao orgDao;
    @Autowired
    LocationDao locationDao;
    @Autowired
    HeroDao heroDao;
    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    /**
     * GET - load all organizations from db
     *
     * @param model {Model} holds relevant data for obj
     * @return {String} load main subdomain
     */
    @GetMapping("organization")
    public String displayOrganizations(Model model) {
        List<Organization> orgs = orgDao.readAllOrganizations();
        List<Hero> heroes = heroDao.readAllHeroes();
        List<Location> locations = locationDao.readAllLocations();

        model.addAttribute("organizations", orgs);
        model.addAttribute("locations", locations);
        model.addAttribute("heroes", heroes);
        model.addAttribute("errors", violations);

        return "organization";
    }

    /**
     * POST - add a new Organization to db
     *
     * @param org     {Organization} a well formed obj
     * @param request {HttpServletRequest} will retrieve form data for new
     *                organization
     * @return {String} redirect to subdomain
     */
    @PostMapping("addOrganization")
    public String addOrganization(Organization org, HttpServletRequest request) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        String locationId = request.getParameter("locationId");
        String[] heroesId = request.getParameterValues("heroId");

        org.setLocation(locationDao.readLocationById(Integer.parseInt(locationId)));

        List<Hero> heroes = new ArrayList<>();
        if (heroesId != null) {
            for (String id : heroesId) {
                heroes.add(heroDao.readHeroById(Integer.parseInt(id)));
            }
        }
        org.setMembers(heroes);

        violations = validate.validate(org);
        if (violations.isEmpty()) {
            orgDao.createOrganization(org);
        }

        return "redirect:/organization";
    }

    /**
     * GET - get org from db
     *
     * @param id    {Integer} a existing organization id
     * @param model {Model} holds org from db
     * @return {String} load subdomain organizationDetails
     */
    @GetMapping("displayOrgDetails")
    public String displayDetails(Integer id, Model model) {
        Organization org = orgDao.readOrganizationById(id);

        model.addAttribute("organization", org);

        return "organizationDetails";
    }

    //FIXME is this used?
    @GetMapping("displayOrgsForHero")
    public String diplayOrgsForHero(Model model) {
        List<Organization> orgs = orgDao.readAllOrganizations();
        model.addAttribute("organizations", orgs);
        return "displayOrgsForHero";
    }

    /**
     * GET - get org from db
     *
     * @param id    {Integer} organization id
     * @param model {Model} to send the org to form
     * @return {String} to editOrganization page
     */
    @GetMapping("editOrganization")
    public String updateOrganization(Integer id, Model model) {
        Organization org = orgDao.readOrganizationById(id);
        List<Location> locations = locationDao.readAllLocations();
        List<Hero> heroes = heroDao.readAllHeroes();

        model.addAttribute("organization", org);
        model.addAttribute("locations", locations);
        model.addAttribute("heroes", heroes);

        return "editOrganization";
    }

    /**
     * POST - update org to db
     *
     * @param org     {Organization} a well formed obj
     * @param result  {BindingResult} holds validation errors
     * @param request {HttpServletRequest} will retrieve edit form data
     * @param model   {Model} holds obj and related data on reload for failure
     *                to edit
     * @return {String} redirect to home page if successful, reload with errors
     *         if fails
     */
    @PostMapping("editOrganization")
    public String updateOrganization(@Valid Organization org, BindingResult result,
            HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            return "editOrganization";
        }

        String locationId = request.getParameter("locationId");
        String[] heroIds = request.getParameterValues("heroId");

        org.setLocation(locationDao.readLocationById(Integer.parseInt(locationId)));

        List<Hero> heroes = new ArrayList<>();
        if (heroIds != null) {
            for (String id : heroIds) {
                heroes.add(heroDao.readHeroById(Integer.parseInt(id)));
            }
        } else {
            FieldError error = new FieldError("organization", "members", "Must include one Member");
            result.addError(error);
        }
        org.setMembers(heroes);

        if (result.hasErrors()) {
            model.addAttribute("organization", org);
            model.addAttribute("locations", locationDao.readAllLocations());
            model.addAttribute("heroes", heroDao.readAllHeroes());
            return "editOrganization";
        }

        orgDao.updateOrganization(org);

        return "redirect:/organization";
    }

    /**
     * GET - load delete confirmation page for a organization from db
     *
     * @param request {HttpServletRequest} will pull in id to retrieve obj
     * @param model   {Model} will hold relevant data for sighting
     * @return {String} load page with organization to be deleted
     */
    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = orgDao.readOrganizationById(id);
        List<Hero> members = orgDao.readHeroesForOrganization(organization);

        model.addAttribute("organization", organization);
        model.addAttribute("location", organization.getLocation());
        model.addAttribute("members", members);

        return "deleteOrganization";
    }

    /**
     * GET - delete a organization from db
     *
     * @param request {HttpServletRequest} will pull in id for delete query
     * @return {String} redirect to organization homepage
     */
    @GetMapping("performDeleteOrganization")
    public String performDeleteOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        orgDao.deleteOrganizationById(id);
        
        return "redirect:/organization";
    }
}

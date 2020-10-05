package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.HeroDao;
import com.sg.m4herosightings.dao.LocationDao;
import com.sg.m4herosightings.dao.OrganizationDao;
import com.sg.m4herosightings.dao.SightingDao;
import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Hero;
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

    /*MAIN SUBDOMAIN*/
    /**
     * GET - on loading subdomain, load all heroes from db to be rendered to
     * table
     *
     * @param model {Model} will hold all heroes from db
     * @return {String} same subdomain
     */
    @GetMapping("heroes")
    public String displayHeroes(Model model) {
        List<Superpower> superpowers = spDao.readAllSuperpowers();
        List<Hero> heroes = hDao.readAllHeroes();
        model.addAttribute("heroes", heroes);
        model.addAttribute("superpowers", superpowers);

        return "heroes";
    }
    
    /**
     * POST - attempt to create a new Hero in db
     *
     * @param hero    {Hero} a valid Hero obj from template engine
     * @param result  {BindingResult} Will hold validation errors for Hero
     *                creation
     * @param request {HttpServletRequest}
     * @param model   {Model} will hold Superpowers and Heroes from db
     * @return {String} reload page if has errors, redirect to heroes subdomain
     *         if successful
     */
    @PostMapping("addHero")
    public String addHero(@Valid Hero hero, BindingResult result,
            HttpServletRequest request, Model model) {
        String superpowerId = request.getParameter("superpowerId");

        //verify that a superpower was selected
        if (superpowerId != null) {
            hero.setSuperpower(spDao.readSuperpowerById(Integer.parseInt(superpowerId)));
        } else {
            FieldError error = new FieldError("hero", "superpowerId", "A hero needs a superpower!");
            result.addError(error);
        }

        //reload if has errors
        if (result.hasErrors()) {
            model.addAttribute("superpowers", spDao.readAllSuperpowers());
            model.addAttribute("hero", hero);

            return "addHero";
        }

        //validate inputs and create hero if all clear
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);

        if (violations.isEmpty()) {
            hDao.createHero(hero);
        }

        return "redirect:/heroes";
    }

    /*DETAILS*/
    /**
     * GET - view a Hero's details
     *
     * @param id    {Integer} a valid id retrieved from template
     * @param model {Model} will hold the retrieve Hero obj
     * @return {String} load page with obj added to model
     */
    @GetMapping("viewHero")
    public String viewHeroDetails(Integer id, Model model) {
        Hero hero = hDao.readHeroById(id);
        model.addAttribute("hero", hero);
        model.addAttribute("superpower", hero.getSuperpower());

        return "viewHero";
    }

    /*EDIT*/
    /**
     * GET - attempt to edit a hero in db
     *
     * @param id    {Integer} a valid id for hero existing in db
     * @param model {Model} will hold the retrieved original hero obj
     * @return {String} load page with obj in model
     */
    @GetMapping("editHero")
    public String editHero(Integer id, Model model) {
        List<Superpower> superpowers = spDao.readAllSuperpowers();
        Hero hero = hDao.readHeroById(id);

        model.addAttribute("hero", hero);
        model.addAttribute("superpowers", superpowers);

        return "editStudent";
    }

    /**
     * POST - perform the edit of a Hero in db
     *
     * @param hero   {Hero} obj to be validated
     * @param result {BindingResult} holds validation errors for hero editing
     * @return {String} reload page if failed, redirect to subdomain if
     *         successful
     */
    @PostMapping("editHero")
    public String performEditStudent(@Valid Hero hero, BindingResult result) {
        if (result.hasErrors()) {
            return "editStudent";
        }

        hDao.updateHero(hero);

        return "redirect:/heroes";
    }

    /*DELETE*/
    /**
     * GET - delete a Hero from db
     *
     * @param id {Integer} a valid id for hero existing in db
     * @return {String} redirect to subdomain if successful
     */
    @GetMapping("deleteHero")
    public String deleteHero(Integer id) {
        hDao.deleteHeroById(id);

        return "redirect:/heroes";
    }
}

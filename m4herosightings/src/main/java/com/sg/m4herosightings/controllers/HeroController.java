/*
    TODO for search bar - have it do a query with the inputted string
 */
package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.HeroDao;
import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Superpower;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HeroController {

    @Autowired
    SuperpowerDao spDao;
    @Autowired
    HeroDao hDao;
    Set<ConstraintViolation<Hero>> violations = new HashSet<>();

    /*MAIN SUBDOMAIN*/
    /**
     * GET - on loading subdomain, load all heroes from db to be rendered to
     * table
     *
     * @param model {Model} will hold all heroes from db
     * @return {String} same subdomain
     */
    @GetMapping("hero")
    public String displayHeroes(Model model) {
        List<Superpower> superpowers = spDao.readAllSuperpowers();
        List<Hero> heroes = hDao.readAllHeroes();
        model.addAttribute("heroes", heroes);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("errors", violations);

        return "hero";
    }

    /**
     * POST - attempt to create a new Hero in db
     *
     * @param request
     * @return {String} reload page if has errors, redirect to heroes subdomain
     *         if successful
     */
    @PostMapping("addHero")
    public String addHero(HttpServletRequest request) {
        String superpowerId = request.getParameter("superpowerId");

        Hero hero = new Hero();
        hero.setSuperpower(spDao.readSuperpowerById(Integer.parseInt(superpowerId)));
        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);

        if (violations.isEmpty()) {
            hDao.createHero(hero);
        }

        return "redirect:/hero";
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
     * @param request
     * @param model   {Model} will hold the retrieved original hero obj
     * @return {String} load page with obj in model
     */
    @GetMapping("editHero")
    public String editHero(HttpServletRequest request, Model model) {
        List<Superpower> superpowers = spDao.readAllSuperpowers();
        Hero hero = hDao.readHeroById(Integer.parseInt(request.getParameter("id")));

        model.addAttribute("superpowers", superpowers);
        model.addAttribute("hero", hero);

        model.addAttribute("errors", violations);

        return "editHero";
    }

    /**
     * POST - perform the edit of a Hero in db
     *
     * @param request
     * @param model
     * @return {String} reload page if failed, redirect to subdomain if
     *         successful
     */
    @PostMapping("editHero")
    public String performEditHero(HttpServletRequest request, Model model) {
        List<Superpower> superpowers = spDao.readAllSuperpowers();
        
        int heroId = Integer.parseInt(request.getParameter("id"));
        Hero hero = hDao.readHeroById(heroId);

        int spId = Integer.parseInt(request.getParameter("superpower"));
        Superpower sp = spDao.readSuperpowerById(spId);
        hero.setSuperpower(sp);

        String heroName = request.getParameter("name");
        String heroDescription = request.getParameter("description");

        hero.setName(heroName);
        hero.setDescription(heroDescription);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(hero);

        if (violations.isEmpty()) {
            hDao.updateHero(hero);
        } else {
            model.addAttribute("superpowers", superpowers);
            model.addAttribute("hero", hero);

            model.addAttribute("errors", violations);

            return "editHero";
        }

        return "redirect:/hero";
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

        return "redirect:/hero";
    }

}

//TODO for search bar - have it do a query with the inputted string
package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.HeroDao;
import com.sg.m4herosightings.dao.ImageDao;
import com.sg.m4herosightings.dao.OrganizationDao;
import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Organization;
import com.sg.m4herosightings.dto.Superpower;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HeroController {

    @Autowired
    SuperpowerDao spDao;
    @Autowired
    HeroDao hDao;
    @Autowired
    OrganizationDao oDao;
    @Autowired
    ImageDao iDao;
    private final String heroUploadDir = "Heroes";
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
     * @param request {HttpServletRequest} pulls in form data
     * @param file    {MultipartFile} an user uploaded image
     * @return {String} reload page if has errors, redirect to heroes subdomain
     *         if successful
     */
    @PostMapping("addHero")
    public String addHero(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String filePath = iDao.saveImage(file, Long.toString(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)), heroUploadDir);

        Hero hero = new Hero();
        hero.setSuperpower(spDao.readSuperpowerById(Integer.parseInt(request.getParameter("superpowerId"))));
        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));
        hero.setPhotoFileName(filePath);

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
        List<Organization> organizations = oDao.displayOrganizationForHero(hero);

        model.addAttribute("hero", hero);
        model.addAttribute("superpower", hero.getSuperpower());
        model.addAttribute("organizations", organizations);

        return "viewHero";
    }

    /*EDIT*/
    /**
     * GET - attempt to edit a hero in db
     *
     * @param request {HttpServletRequest} will pull in edit form data
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
     * @param request {HttpServletRequest} will pull in edit form data
     * @param file    {MultipartFile} an user uploaded image to replace original
     * @param model   {Model} will hold the retrieved original hero obj
     * @return {String} reload page if failed, redirect to subdomain if
     *         successful
     */
    @PostMapping("editHero")
    public String performEditHero(HttpServletRequest request,
            @RequestParam("file") MultipartFile file, Model model) {
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
        hero.setPhotoFileName(iDao.updateImage(file, hero.getPhotoFileName(), heroUploadDir));

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
     * @param request {HttpServletRequest} will pull in id to retrieve obj
     * @param model   {Model} will hold relevant data for Hero
     * @return {String} load page with hero to be deleted
     */
    @GetMapping("deleteHero")
    public String deleteHero(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero hero = hDao.readHeroById(id);
        List<Organization> organizations = oDao.displayOrganizationForHero(hero);

        model.addAttribute("hero", hero);
        model.addAttribute("superpower", hero.getSuperpower());
        model.addAttribute("organizations", organizations);

        return "deleteHero";
    }

    /**
     * GET - delete a hero from db
     *
     * @param request {HttpServletRequest} will pull in id for delete query
     * @return {String} redirect to hero
     */
    @GetMapping("performDeleteHero")
    public String performDeleteHero(HttpServletRequest request) {
        int heroId = Integer.parseInt(request.getParameter("id"));
        Hero hero = hDao.readHeroById(heroId);

        iDao.deleteImage(hero.getPhotoFileName());
        hDao.deleteHeroById(heroId);

        return "redirect:/hero";
    }

}

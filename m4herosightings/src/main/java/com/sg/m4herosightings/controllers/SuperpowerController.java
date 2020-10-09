package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Superpower;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SuperpowerController {

    @Autowired
    SuperpowerDao spDao;
    Set<ConstraintViolation<Superpower>> violations = new HashSet<>();

    /**
     * GET - load all superpowers from db for rendering
     *
     * @param model {Model} will hold a list of all superpowers and any errors
     * @return {String} load homepage
     */
    @GetMapping("superpower")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = spDao.readAllSuperpowers();

        model.addAttribute("superpowers", superpowers);
        model.addAttribute("errors", violations);

        return "superpower";
    }

    /**
     * POST - add a new superpower to db
     *
     * @param superpower {Superpower} a validated superpower obj
     * @param result     {BindingResult} enforces validation rules
     * @return {String} reload homepage
     */
    @PostMapping("addSuperpower")
    public String addSuperpower(@Valid Superpower superpower, BindingResult result) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);

        if (violations.isEmpty()) {
            spDao.createSuperpower(superpower);
        }

        return "redirect:/superpower";
    }

    /*DETAILS*/
    /**
     * GET - retrieve a superpower for rendering
     *
     * @param id    {Integer} id of an existing superpower
     * @param model {Model} holds obj
     * @return {String} load details page
     */
    @GetMapping("viewSuperpower")
    public String viewSuperpowerDetails(Integer id, Model model) {
        Superpower superpower = spDao.readSuperpowerById(id);

        model.addAttribute("superpower", superpower);

        return "viewSuperpower";
    }

    /*EDIT*/
    /**
     * GET - retrieve original Superpower to be edited
     *
     * @param id    {Integer} the id of the superpower to be edited
     * @param model {Model} holds obj
     * @return {String} load edit page
     */
    @GetMapping("editSuperpower")
    public String editSuperpower(Integer id, Model model) {
        Superpower superpower = spDao.readSuperpowerById(id);

        model.addAttribute("superpower", superpower);

        return "viewSuperpower";
    }

    /**
     * POST - attempt to edit a superpower in db
     *
     * @param superpower {Superpower} a validated obj
     * @param result     {BindingResult} enforces validation rules
     * @param model      {Model} holds obj on reload for failure to edit
     * @return {String} redirect to homepage if successful, reload otherwise
     */
    @PostMapping("editSuperpower")
    public String performEditSuperpower(@Valid Superpower superpower, BindingResult result, Model model) {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superpower);

        if (result.hasErrors()) {
            model.addAttribute("superpower", superpower);

            return "editSuperpower";
        }

        if (violations.isEmpty()) {
            spDao.updateSuperpower(superpower);
        }

        return "redirect:/superpower";
    }

    /*DELETE*/
    /**
     * DELETE - delete a superpower from db
     *
     * @param id {Integer} id of an existing superpower
     * @return {String} redirect to homepage
     */
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(Integer id) {
        spDao.deleteSuperpowerById(id);

        return "redirect:/superpower";
    }
}

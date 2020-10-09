package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Superpower;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuperpowerController {

    @Autowired
    SuperpowerDao spDao;
    Set<ConstraintViolation<Superpower>> violations = new HashSet<>();

    /**
     * GET - load all superpowers from server for rendering
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
}
